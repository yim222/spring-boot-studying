// tag::follow-2[]

import follow from '../../../follow'
import client from '../../../client'
import when from 'when'
const root = '/api';
export function loadFromServer(pageSize, entityName) {
  return (
      follow(client, root, [
      {rel: "chessSquare2s", params: {size: pageSize}}]
    ).then(entityResponseCollection => {

      return client({
        method: 'GET',
        path: entityResponseCollection.entity._links.profile.href,
        headers: {'Accept': 'application/schema+json'}
      }).then(schema => {
        console.log ("schema.entity === ",schema.entity)
        //this.schema = schema.entity;
        //this.links = entityResponseCollection.entity._links;
        schema = schema.entity;
        const links = entityResponseCollection.entity._links;
        return entityResponseCollection;
      });
    }).then(entityResponseCollection => {
      return entityResponseCollection.entity._embedded.entityResponses.map(entityResponse =>
          client({
            method: 'GET',
            path: entityResponse._links.self.href
          })
      );
    })
  );
  /*
  .then(entityResponsePromises => {
    return when.all(entityResponsePromises);
  }).done(entityResponses => {
    this.setState({
      entityResponses: entityResponses,
      attributes: Object.keys(this.schema.properties),
      pageSize: pageSize,
      links: this.links
    });
  });
  */
}
// end::follow-2[]
