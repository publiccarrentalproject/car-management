import React, { Component } from 'react'

import HeaderComponent from './HeaderComponent'
import CarListComponent from './CarListComponent'
import FooterComponent from './FooterComponent'
import CarSearchComponent from './CarSearchComponent'
import AddDeleteButtonsComponent from './AddDeleteButtonsComponent'



class CarAdminComponent extends Component {
    render() {
        return (
            <div className="CarAdmin">
                <p><HeaderComponent /></p>
                <p><CarSearchComponent /></p>
                <p><AddDeleteButtonsComponent /></p>
                <p><CarListComponent /></p>
                <p><FooterComponent /></p>
            </div>
        )
    }
}

export default CarAdminComponent