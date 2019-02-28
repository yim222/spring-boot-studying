'use strict';
const React = require('react');
const ReactDOM = require('react-dom');
const when = require('when');
const client = require('./client');
const root = ('/api')
const follow = require('./follow'); // function to hop multiple links by "rel"



class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {socialEvents: [], attributes: [], pageSize: 3, links: {}};
		
		this.updatePageSize = this.updatePageSize.bind(this);
		this.onCreate = this.onCreate.bind(this);
		this.onUpdate = this.onUpdate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
	}

	componentDidMount() {
		
		this.loadFromServer(this.state.pageSize);
	}
	//for load from server 
	loadFromServer(pageSize) {
		follow(client, root, [
			{rel: 'socialEvents', params: {size: pageSize}}]
			).then(socialEventCollection => {
				return client({
					method: 'GET',
					path: socialEventCollection.entity._links.profile.href,
					headers: {'Accept': 'application/schema+json'}
				}).then(schema => {
					this.schema = schema.entity;
					this.links = socialEventCollection.entity._links;
					return socialEventCollection;
				});//U here   
			}).then(socialEventCollection => {
				return socialEventCollection.entity._embedded.socialEvents.map(socialEvent =>
				client({
					method: 'GET',
					path: socialEvent._links.self.href
				})
				);
			}).then(socialEventPromises => {
				return when.all(socialEventPromises);
			}).done(socialEvents => {
				this.setState({
					socialEvents: socialEvents,
					attributes: Object.keys(this.schema.properties),
					pageSize: pageSize,
					links: this.links 
			});
		});
	}
	
	// tag::create[]
	onCreate(newSocialEvent) {
		follow(client, root, ['socialEvents']).then(socialEventCollection => {
			return client({
				method: 'POST',
				path: socialEventCollection.entity._links.self.href,
				entity: newSocialEvent,
				headers: {'Content-Type': 'application/json'}
			})
		}).then(response => {
			return follow(client, root, [
				{rel: 'socialEvents', params: {'size': this.state.pageSize}}]);
		}).done(response => {
			if (typeof response.entity._links.last !== "undefined") {
				this.onNavigate(response.entity._links.last.href);
			} else {
				this.onNavigate(response.entity._links.self.href);
			}
		});
	}
	
	// tag::update[]
	onUpdate(socialEvent, updatedSocialEvent) {
		client({
			method: 'PUT',
			path: socialEvent.entity._links.self.href,
			entity: updatedSocialEvent,
			headers: {
				'Content-Type': 'application/json',
				'If-Match': socialEvent.headers.Etag
			}
		}).done(response => {
			this.loadFromServer(this.state.pageSize);
		}, response => {
			if (response.status.code === 412) {
				alert('DENIED: Unable to update ' +
					socialEvent.entity._links.self.href + '. Your copy is stale.');
			}
		});
	}
	// end::update[]

	
	
	// tag::navigate[]
	onNavigate(navUri) {
		client({
			method: 'GET',
			path: navUri
		}).then(socialEventCollection => {
			this.links = socialEventCollection.entity._links;

			return socialEventCollection.entity._embedded.socialEvents.map(socialEvent =>
					client({
						method: 'GET',
						path: socialEvent._links.self.href
					})
			);
		}).then(socialEventPromises => {
			return when.all(socialEventPromises);
		}).done(socialEvents => {
			this.setState({
				socialEvents: socialEvents,
				attributes: Object.keys(this.schema.properties),
				pageSize: this.state.pageSize,
				links: this.links
			});
		});
	}
	// end::navigate[]

	
	// tag::handle-nav[]
	handleNavFirst(e){
		e.preventDefault();
		this.props.onNavigate(this.props.links.first.href);
	}

	handleNavPrev(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.prev.href);
	}

	handleNavNext(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.next.href);
	}

	handleNavLast(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.last.href);
	}
	// end::handle-nav[]
	
	// tag::delete[]
	onDelete(socialEvent) {
		client({method: 'DELETE', path: socialEvent._links.self.href}).done(response => {
			this.loadFromServer(this.state.pageSize);
		});
	}
	//end::delete[]
	
	// tag::update-page-size[]
	updatePageSize(pageSize) {
		if (pageSize !== this.state.pageSize) {
			this.loadFromServer(pageSize);
		}
	}
	// end::update-page-size[]
	render() {
		
		
		return (
			<div>
					<h1>Trying Social Evnets - Spring & React</h1> 
					<CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
					<SocialEventList socialEvents={this.state.socialEvents}
								  links={this.state.links}
								  pageSize={this.state.pageSize}
								  onNavigate={this.onNavigate}
								  onDelete={this.onDelete}
								  updatePageSize={this.updatePageSize}/>
			</div>
		)
	}
}

class SocialEvents extends React.Component{
	
	
	
	render() {
		const socialEvents = this.props.socialEvents.map(socialEvent =>
			<SocialEvent key={socialEvent._links.self.href} socialEvent={socialEvent}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Description</th>
						<th>From Age</th>
						<th>To Age</th>
					</tr>
					{socialEvents}
				</tbody>
			</table>
		)
	}
}

class SocialEvent extends React.Component{
	constructor(props){
		super();
		this.handleDelete = this.handleDelete.bind(this);
		
	}
	handleDelete() {
		this.props.onDelete(this.props.socialEvent);
	}
	render() {
		return (
			<tr>
				<td>{this.props.socialEvent.description}</td>
				<td>{this.props.socialEvent.fromAge}</td>
				<td>{this.props.socialEvent.toAge}</td>
				<td>
				<UpdateDialog socialEvent={this.props.socialEvent}
							  attributes={this.props.attributes}
							  onUpdate={this.props.onUpdate}/>
				</td>
				<td>
					<button onClick={this.handleDelete}>Delete</button>
				</td>
			</tr>
		)
	}
}

class CreateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		const newSocialEvent = {};
		this.props.attributes.forEach(attribute => {
			newSocialEvent[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onCreate(newSocialEvent);

		// clear out the dialog's inputs
		this.props.attributes.forEach(attribute => {
			ReactDOM.findDOMNode(this.refs[attribute]).value = '';
		});

		// Navigate away from the dialog to hide it.
		window.location = "#";
	}

	render() {
		const inputs = this.props.attributes.map(attribute =>
			<p key={attribute}>
				<input type="text" placeholder={attribute} ref={attribute} className="field"/>
			</p>
		);

		return (
			<div>
				<a href="#createSocialEvent">Create</a>

				<div id="createSocialEvent" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Create new social Event</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Create</button>
						</form>
					</div>
				</div>
			</div>
		)
	}

}
//tag::update-dialog[]
class UpdateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		const updatedSocialEvent = {};
		this.props.attributes.forEach(attribute => {
			updatedSocialEvent[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onUpdate(this.props.socialEvent, updatedSocialEvent);
		window.location = "#";
	}

	render() {
		const inputs = this.props.attributes.map(attribute =>
			<p key={this.props.socialEvent.entity[attribute]}>
				<input type="text" placeholder={attribute}
					   defaultValue={this.props.socialEvent.entity[attribute]}
					   ref={attribute} className="field"/>
			</p>
		);

		const dialogId = "updateSocialEvent-" + this.props.socialEvent.entity._links.self.href;

		return (
			<div key={this.props.socialEvent.entity._links.self.href}>
				<a href={"#" + dialogId}>Update</a>
				<div id={dialogId} className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Update an socialEvent</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Update</button>
						</form>
					</div>
				</div>
			</div>
		)
	}

};
// end::update-dialog[]


// tag: :SocialEventList
class SocialEventList extends React.Component {

	constructor(props) {
		super(props);
		this.handleNavFirst = this.handleNavFirst.bind(this);
		this.handleNavPrev = this.handleNavPrev.bind(this);
		this.handleNavNext = this.handleNavNext.bind(this);
		this.handleNavLast = this.handleNavLast.bind(this);
		this.handleInput = this.handleInput.bind(this);
	}

	// tag::handle-page-size-updates[]
	handleInput(e) {
		e.preventDefault();
		const pageSize = ReactDOM.findDOMNode(this.refs.pageSize).value;
		if (/^[0-9]+$/.test(pageSize)) {
			this.props.updatePageSize(pageSize);
		} else {
			ReactDOM.findDOMNode(this.refs.pageSize).value =
				pageSize.substring(0, pageSize.length - 1);
		}
	}
	// end::handle-page-size-updates[]

	// tag::handle-nav[]
	handleNavFirst(e){
		e.preventDefault();
		this.props.onNavigate(this.props.links.first.href);
	}

	handleNavPrev(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.prev.href);
	}

	handleNavNext(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.next.href);
	}

	handleNavLast(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.last.href);
	}
	// end::handle-nav[]

	// tag::socialEvent-list-render[]
	render() {
		const socialEvents = this.props.socialEvents.map(socialEvent =>
			<SocialEvent 
			key={socialEvent.entity._links.self.href} 
			socialEvent={socialEvent} 
			attributes={this.props.attributes}
			onUpdate={this.props.onUpdate}
			onDelete={this.props.onDelete}/>
		);

		const navLinks = [];
		if ("first" in this.props.links) {
			navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
		}
		if ("prev" in this.props.links) {
			navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
		}
		if ("next" in this.props.links) {
			navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
		}
		if ("last" in this.props.links) {
			navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
		}

		return (
			<div>
				<input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
				<table>
					<tbody>
						<tr>
							<th>Description</th>
							<th>From Age</th>
							<th>To Age</th>
							<th></th>
						</tr>
						{socialEvents}
					</tbody>
				</table>
				<div>
					{navLinks}
				</div>
			</div>
		)
	}
	// end::socialEvent-list-render[]
}
	


ReactDOM.render(
		<App />,
		document.getElementById('react')
	)