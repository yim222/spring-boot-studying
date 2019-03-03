import React from 'react'
//import {loadFromServer} from '../services/GetData'
import client from '../../../client'
import follow from '../../../follow'

export class ChessSquare extends React.Component{

  constructor(props){
    super();
    this.state = {
      chessSquares : []
    }

  }
  componentDidMount() {
	  /*
		loadFromServer(2, "chessSquare2s")
    .then(entityResponsePromises => {
      return when.all(entityResponsePromises);
    }).done(entityResponses => {
      this.setState({
        chessSquare2s: "entityResponses222",
        attributes: Object.keys(this.schema.properties),
        pageSize: 2,
        links: this.links
      });
    });
    */
	  this.loadFromServer(this.state.pageSize);
	}
//tag::follow-2[]
	loadFromServer(pageSize) {
		follow(client, root, [
			{rel: 'chessSquare2s', params: {size: pageSize}}]
		).then(chessSquare2Collection => {
			return client({
				method: 'GET',
				path: chessSquare2Collection.entity._links.profile.href,
				headers: {'Accept': 'application/schema+json'}
			}).then(schema => {
				this.schema = schema.entity;
				this.links = chessSquare2Collection.entity._links;
				return chessSquare2Collection;
			});
		}).then(chessSquare2Collection => {
			return chessSquare2Collection.entity._embedded.chessSquare2s.map(chessSquare2 =>
					client({
						method: 'GET',
						path: chessSquare2._links.self.href
					})
			);
		}).then(chessSquare2Promises => {
			return when.all(chessSquare2Promises);
		}).done(chessSquare2s => {
			this.setState({
				chessSquare2s: chessSquare2s,
				attributes: Object.keys(this.schema.properties),
				pageSize: pageSize,
				links: this.links
			});
		});
	}
	// end::follow-2[]
  render(){

    console.log("Some playing - updated");
    var rest = require('rest');

    rest('/').then(function(response) {
      console.log('response: ', response);
      //http://localhost:8080/api/socialEvents

    });
    rest('http://localhost:8080/api/socialEvents/')
    .then(function(response) {
      console.log('response: ', response);
    });
    return (
      <div>
      	<h1>Try1</h1>
        <h3>I am ChessSquare</h3>
        <h3>ChessSquares Arrays </h3>
        <h4>{this.state.chessSquare2s}</h4>
      </div>
    )
  }
}
