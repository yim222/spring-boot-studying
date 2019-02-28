import React from 'react'
import { Link } from 'react-router-dom'

// The Header creates links that can be used to navigate
// between routes.
const MainMenu = () => (


      <ul  className="nav navbar-nav footer1">
        <li ><Link to='/'>Home</Link></li>
        <li><Link to='/screen1'>Screen 1 </Link></li>

      </ul>


)

export default MainMenu
