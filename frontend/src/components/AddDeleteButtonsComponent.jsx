import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button'

class AddDeleteButtonsComponent extends Component {
    render() {
        return (
            <form noValidate autoComplete="off">
                <Grid container spacing={3}>
                    <Grid item xs={3} spacing={1}>
                        <Button variant="outlined" color="primary">
                            Add New Car
                        </Button>
                    </Grid>
                    <Grid item xs={3} spacing={1}>
                        <Button variant="outlined" color="primary">
                            Delete Car(s)
                        </Button>
                    </Grid>
                </Grid>
            </form >
        )
    }
}

export default AddDeleteButtonsComponent;