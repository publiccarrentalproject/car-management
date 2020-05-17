import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button'

class AddDeleteButtonsComponent extends Component {
    render() {
        return (
            <Grid container spacing={3}>
                <Grid item xs={2} spacing={1}>
                    <Button variant="outlined" color="primary">
                        Delete Car(s)
                    </Button>
                </Grid>

                <Grid item xs={7} spacing={1}>
                    <Button variant="outlined" color="primary">
                        Add New Car
                    </Button>
                </Grid>
            </Grid>
        )
    }
}

export default AddDeleteButtonsComponent;