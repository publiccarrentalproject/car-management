import React, { Component } from 'react';
import { withRouter } from 'react-router-dom'

import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button'

class AddDeleteButtonsComponent extends Component {
    constructor(props) {
        super(props)
        this.addCarClicked = this.addCarClicked.bind(this)
    }

    addCarClicked() {
        this.props.history.push(`/rest/car/-1`)
    }

    render() {
        return (
            <Grid container spacing={3}>
                <Grid item xs={2} spacing={1}>
                    <Button variant="outlined" color="primary">
                        Delete Car(s)
                    </Button>
                </Grid>

                <Grid item xs={7} spacing={1}>
                    <Button variant="outlined" color="primary" onClick={() => this.addCarClicked()}>Add a New Car</Button>
                </Grid>
            </Grid>
        )
    }
}

export default withRouter(AddDeleteButtonsComponent);