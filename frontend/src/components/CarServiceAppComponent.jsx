import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import CarAdminComponent from './CarAdminComponent'
import CarComponent from './CarComponent'
import ErrorComponent from './ErrorComponent'

class CarServiceAppComponent extends Component {

    render() {
        return (
            <div className="CarServiceApp">
                <Router>
                    <Switch>
                        <Route path="/" exact component={CarAdminComponent} />
                        <Route path="/rest/car/:id" component={CarComponent} />
                        <Route component={ErrorComponent} />
                    </Switch>
                </Router>
            </div>
        )
    }
}
export default CarServiceAppComponent; 