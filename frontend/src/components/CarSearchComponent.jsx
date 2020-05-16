import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField'
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button'

class CarSearchComponent extends Component {
    render() {
        return (
            <form noValidate autoComplete="off">
                <Grid container spacing={3}>
                    <Grid item xs={2} spacing={1}>
                        <TextField id="plateNumber" label="Plate Number" variant="outlined" />
                    </Grid>
                    <Grid item xs={2} spacing={1}>
                        <TextField id="brand" label="Brand" variant="outlined" />
                    </Grid>
                    <Grid item xs={2} spacing={1}>
                        <TextField id="model" label="Model" variant="outlined" />
                    </Grid>
                    <Grid item xs={6} spacing={1}>
                    </Grid>

                    <Grid item xs={2} spacing={3}>
                        <TextField id="year" label="Year" variant="outlined" />
                    </Grid>
                    <Grid item xs={2} spacing={3}>
                        <TextField id="fuelType" label="Fuel Type" variant="outlined" />
                    </Grid>
                    <Grid item xs={2} spacing={3}>
                        <TextField id="color" label="Color" variant="outlined" />
                    </Grid>
                    <Grid item xs={6} spacing={1}>
                    </Grid>
                    <Grid item xs={6} spacing={1} justify="flex-start">
                        <Button variant="outlined" color="primary">
                            Search
                        </Button>
                    </Grid>
                    <Grid item xs={6} spacing={1}>
                    </Grid>

                </Grid>

            </form>
        )
    }
}

export default CarSearchComponent;