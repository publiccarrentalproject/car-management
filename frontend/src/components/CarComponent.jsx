
import React, { Component } from 'react'
import CarDataService from '../api/car/CarDataService.js'
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import HeaderComponent from './HeaderComponent';
import FooterComponent from './FooterComponent';
import { Formik, Form, Field, ErrorMessage } from 'formik'


class CarComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            plateNumber: '',
            brand: '',
            model: '',
            year: '',
            fuelType: '',
            color: ''
        }
    }

    onSubmit(values) {
        console.log(values)
        let car = {
            plateNumber: values.plateNumber,
            brand: values.brand,
            model: values.model,
            year: values.year,
            fuelType: values.fuelType,
            color: values.color,
            fuelConsumption: values.fuelConsumption,
            numberOfDoors: values.numberOfDoors,
            numberOfSeats: values.numberOfSeats
        };
        CarDataService.createCar(car)
            .then(res => {
                this.setState({ message: 'The car was added successfully.' });
                this.props.history.push('/');
            });
    }

    render() {
        return (
            <div>
                <Grid container spacing={3}>
                    <Grid item xs={6} spacing={1}>
                        <HeaderComponent submenu="Add a Car" />
                    </Grid>
                </Grid>
                <Formik
                    validate={this.validate}
                    onSubmit={this.onSubmit}
                    validateOnChange={false}
                    validateOnBlur={false}
                    enableReinitialize={true}
                >

                    {
                        (props) => (
                            <Form>

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

                                    <Grid item xs={2} spacing={3}>
                                        <TextField id="fuelConsumption" label="Fuel Consumption" variant="outlined" />
                                    </Grid>
                                    <Grid item xs={2} spacing={3}>
                                        <TextField id="numberOfDoors" label="Number of Doors" variant="outlined" />
                                    </Grid>
                                    <Grid item xs={2} spacing={3}>
                                        <TextField id="numberOfSeats" label="Number of Seats" variant="outlined" />
                                    </Grid>
                                    <Grid item xs={6} spacing={1}>
                                    </Grid>
                                    <Grid item xs={11} spacing={1}>
                                        <Button type="outlined" color="primary" >Save</Button>

                                    </Grid>
                                </Grid>
                            </Form>)
                    }
                </Formik>

                <Grid container spacing={3}>
                    <Grid item xs={6} spacing={1}>
                        <FooterComponent />
                    </Grid>
                </Grid>

            </div>
        );
    }
}

export default CarComponent;