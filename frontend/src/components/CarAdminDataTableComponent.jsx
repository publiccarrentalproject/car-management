import 'primeicons/primeicons.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.css';
import 'primeflex/primeflex.css';
import '../index.css';
import ReactDOM from 'react-dom';

import React, { Component } from 'react';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {Button} from 'primereact/button';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';

import CarDataService from '../api/car/CarDataService'



export class CarAdminDataTableComponent extends Component {

    constructor() {
        super();
        this.state = {
            cars: [],
            selectedCar: null,
            car: null,
            displayDialog: false
        };
        this.onSave = this.onSave.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.onCarSelect = this.onCarSelect.bind(this);
        this.addNew = this.addNew.bind(this);
    }

    componentDidMount() {
        CarDataService.retrieveAllCars()
                    .then(
                        response => {
                            this.setState({
                                cars: response.data
                            })
                        }
                    )
    }

    onSave() {
        let cars = [...this.state.cars];
        if(this.newCar) {
            cars.push(this.state.car);
            this.state.car.rentable=true
            console.log(this.state.car);


            CarDataService.createCar(this.state.car);
        }
        else {
            cars[this.findSelectedCarIndex()] = this.state.car;
            CarDataService.updateCar(this.state.car.plateNumber,this.state.car);
         }

        this.setState({cars:cars, selectedCar:null, car: null, displayDialog:false});


    }

    onDelete() {
        let index = this.findSelectedCarIndex();
        this.setState({
            cars: this.state.cars.filter((val,i) => i !== index),
            selectedCar: null,
            car: null,
            displayDialog: false});

        CarDataService.deleteCar(this.state.car.plateNumber)
    }

    findSelectedCarIndex() {
        return this.state.cars.indexOf(this.state.selectedCar);
    }

    updateProperty(property, value) {
        let car = this.state.car;
        car[property] = value;
        this.setState({car: car});
    }

    onCarSelect(e){
        this.newCar = false;
        this.setState({
            displayDialog:true,
            car: Object.assign({}, e.data)
        });
    }

    addNew() {
        this.newCar = true;
        this.setState({
            car: {plateNumber:'', brand: '', model: '', year: '',
                  engine: '', fuelType: '', fuelConsumption: '',
                  numberOfDoors: '', numberOfSeats: ''},
            displayDialog: true
        });
    }

    render() {

        let header = <div className="p-clearfix" style={{lineHeight:'1.87em'}}>Car Admin Page </div>;

        let footer = <div className="p-clearfix" style={{width:'100%'}}>
            <Button style={{float:'left'}} label="Add" icon="pi pi-plus" onClick={this.addNew}/>
        </div>;

        let dialogFooter = <div className="ui-dialog-buttonpane p-clearfix">
                <Button label="Delete" icon="pi pi-times" onClick={this.onDelete}/>
                <Button label="Save" icon="pi pi-check" onClick={this.onSave}/>
            </div>;

        return (
            <div>
                <div className="content-section implementation">
                    <DataTable value={this.state.cars} paginator={true} rows={15}  header={header} footer={footer}
                               selectionMode="single" selection={this.state.selectedCar} onSelectionChange={e => this.setState({selectedCar: e.value})}
                               onRowSelect={this.onCarSelect}>

                        <Column field="plateNumber" header="Plate Number" sortable={true}/>
                        <Column field="brand" header="Brand" sortable={true}/>
                        <Column field="model" header="Model" sortable={true}/>
                        <Column field="year" header="Year" sortable={true}/>
                        <Column field="engine" header="Engine" sortable={true}/>
                        <Column field="fuelType" header="Fuel Type" sortable={true}/>
                        <Column field="fuelConsumption" header="Fuel Consumption" sortable={true}/>
                        <Column field="numberOfDoors" header="Number of Doors" sortable={true}/>
                        <Column field="numberOfSeats" header="Number of Seats" sortable={true}/>
                    </DataTable>

                    <Dialog visible={this.state.displayDialog} style={{width: '300px'}} header="Car Details" modal={true} footer={dialogFooter} onHide={() => this.setState({displayDialog: false})}
                        blockScroll={false}>
                        {
                            this.state.car &&

                            <div className="p-grid p-fluid">
                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="plateNumber">Plate Number</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="plateNumber" onChange={(e) => {this.updateProperty('plateNumber', e.target.value)}} value={this.state.car.plateNumber}/>
                                </div>
                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="brand">Brand</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="brand" onChange={(e) => {this.updateProperty('brand', e.target.value)}} value={this.state.car.brand}/>
                                </div>

                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="model">Model</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="model" onChange={(e) => {this.updateProperty('model', e.target.value)}} value={this.state.car.model}/>
                                </div>

                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="year">Year</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="year" onChange={(e) => {this.updateProperty('year', e.target.value)}} value={this.state.car.year}/>
                                </div>

                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="engine">Engine</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="engine" onChange={(e) => {this.updateProperty('engine', e.target.value)}} value={this.state.car.engine}/>
                                </div>

                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="fuelType">Fuel Type</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="fuelType" onChange={(e) => {this.updateProperty('fuelType', e.target.value)}} value={this.state.car.fuelType}/>
                                </div>

                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="fuelConsumption">Fuel Consumption</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="fuelConsumption" onChange={(e) => {this.updateProperty('fuelConsumption', e.target.value)}} value={this.state.car.fuelConsumption}/>
                                </div>

                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="numberOfDoors">Number of Doors</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="numberOfDoors" onChange={(e) => {this.updateProperty('numberOfDoors', e.target.value)}} value={this.state.car.numberOfDoors}/>
                                </div>

                                <div className="p-col-4" style={{padding:'.75em'}}><label htmlFor="numberOfSeats">Number of Seats</label></div>
                                <div className="p-col-8" style={{padding:'.5em'}}>
                                    <InputText id="numberOfSeats" onChange={(e) => {this.updateProperty('numberOfSeats', e.target.value)}} value={this.state.car.numberOfSeats}/>
                                </div>

                           </div>
                        }
                    </Dialog>
                </div>
            </div>
        );
    }
}

export default CarAdminDataTableComponent