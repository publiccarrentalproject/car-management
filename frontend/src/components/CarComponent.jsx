
import React, { Component } from 'react'
import CarDataService from '../api/car/CarDataService.js'
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

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
        this.saveCar = this.saveCar.bind(this);
    }

    saveCar = (e) => {
        e.preventDefault();
        let car = {
            plateNumber: this.state.plateNumber,
            brand: this.state.brand,
            model: this.state.model,
            year: this.state.year,
            fuelType: this.state.fuelType,
            color: this.state.color
        };
        CarDataService.createCar(car)
            .then(res => {
                this.setState({ message: 'The car was added successfully.' });
                this.props.history.push('/rest/cars');
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    render() {
        return (
            <div>
                <Typography variant="h4" style={style}>Add Car</Typography>
                <form style={formContainer}>

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
                        <Grid item xs={11} spacing={1}>
                            <Button variant="contained" color="primary" onClick={this.saveCar}>Save</Button>

                        </Grid>

                    </Grid>
                </form>
            </div>
        );
    }
}
const formContainer = {
    display: 'flex',
    flexFlow: 'row wrap'
};

const style = {
    display: 'flex',
    justifyContent: 'center'

}

export default CarComponent;