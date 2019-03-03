import React from 'react'
import {loadFromServer} from '../services/GetData'
import client from '../../../client'

export class ChessSquare extends React.Component{

  constructor(props){
    super();
    this.state = {
      chessSquares : []
    }

  }
  componentDidMount() {
		loadFromServer(2, "chessSquare2s")
    .then(entityResponsePromises => {
      return when.all(entityResponsePromises);
    }).done(entityResponses => {
      this.setState({
        chessSquares: "entityResponses222",
        attributes: Object.keys(this.schema.properties),
        pageSize: 2,
        links: this.links
      });
    });
	}
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

        <h3>I am ChessSquare</h3>
        <h3>ChessSquares Arrays </h3>
        <h4>{this.state.chessSquares}</h4>
      </div>
    )
  }
}
