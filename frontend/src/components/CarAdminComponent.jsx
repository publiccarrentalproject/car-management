import React, { Component } from 'react'

import HeaderComponent from './HeaderComponent'
import CarListComponent from './CarListComponent'
import FooterComponent from './FooterComponent'
import CarSearchComponent from './CarSearchComponent'
import AddDeleteButtonsComponent from './AddDeleteButtonsComponent'
import Grid from '@material-ui/core/Grid';


class CarAdminComponent extends Component {
    render() {
        return (

            <div className="CarAdmin">
                <Grid container spacing={3}>
                    <Grid item xs={6} spacing={1}>
                        <HeaderComponent submenu="Admin Page" />
                    </Grid>
                </Grid>
                <Grid container spacing={3}>
                    <Grid item xs={12} spacing={1}>
                        <CarSearchComponent />
                    </Grid>
                </Grid>
                <Grid container spacing={3}>
                    <Grid item xs={12} spacing={1}>
                        <AddDeleteButtonsComponent />
                    </Grid>
                </Grid>
                <Grid container spacing={3}>
                    <Grid item xs={6} spacing={1}>
                        <CarListComponent />
                    </Grid>
                </Grid>
                <Grid container spacing={3}>
                    <Grid item xs={6} spacing={1}>
                        <FooterComponent />
                    </Grid>
                </Grid>
            </div>
        )
    }
}

export default CarAdminComponent