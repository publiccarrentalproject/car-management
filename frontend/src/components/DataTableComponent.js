import React, { Component } from 'react';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {InputText} from 'primereact/inputtext';
import "./DataTable.scss"

export class DataTableComponent extends Component {
    constructor() {
        super();

        const demoCarList = [
            { plateNumber: 'AA-33-DD', brand: 'Toyota', model: 'Corolla', year: 2011, color: 'Gray'},
            { plateNumber: 'BB-44-CC', brand: 'Volkswagen', model: 'Polo', year: 2015, color: 'White'},
            { plateNumber: 'EE-33-UF', brand: 'Toyota', model: 'Yaris', year: 2018, color: 'Black'},
            { plateNumber: 'HH-13-1F', brand: 'Volkswagen', model: 'Jetta', year: 2017, color: 'Blue'},
            { plateNumber: 'XX-33-AA', brand: 'Volvo', model: 'S60', year: 2018, color: 'White'},
            { plateNumber: 'VV-45-GG', brand: 'Toyota', model: 'Yaris', year: 2010, color: 'White'},
            { plateNumber: 'UO-33-NN', brand: 'Toyota', model: 'Auris', year: 2018, color: 'Black'},
            { plateNumber: 'PO-56-MM', brand: 'Volkswagen', model: 'Jetta', year: 2012, color: 'Blue'},
            { plateNumber: 'JJ-78-NN', brand: 'BMW', model: 'X6', year: 2019, color: 'Blue'},
            { plateNumber: 'EE-23-EF', brand: 'Toyota', model: 'Corolla', year: 2014, color: 'Black'},
            { plateNumber: 'VT-89-CF', brand: 'Toyota', model: 'Yaris', year: 2014, color: 'Black'},
            { plateNumber: 'RG-75-HS', brand: 'Volkswagen', model: 'Polo', year: 2019, color: 'White'},
            { plateNumber: 'XX-33-AA', brand: 'Volvo', model: 'S40', year: 2013, color: 'White'}
          ];

        this.state = {
            cars: demoCarList,
            selectedCars: null,
            globalFilter: null,
        };
    }

    renderHeader() {
        return (
            <div>
                List of Cars
                <div  className="p-datatable-globalfilter-container">
                    <InputText type="search" onInput={(e) => this.setState({globalFilter: e.target.value})} placeholder="Global Search" />
                </div>
            </div>
        );
    }

    render() {
        const header = this.renderHeader();

        return (
            <div className="datatable-doc-demo">
                <div className="content-section introduction">
                    <div className="feature-intro">
                        <h1>Car Service Admin GUI</h1>
                    </div>
                </div>

                <div className="content-section implementation">
                    <DataTable ref={(el) => this.dt = el} value={this.state.cars}
                        header={header} responsive className="p-datatable-cars" dataKey="id" rowHover globalFilter={this.state.globalFilter}
                        selection={this.state.selectedCars} onSelectionChange={e => this.setState({selectedCars: e.value})}
                        paginator rows={10} emptyMessage="No cars found" currentPageReportTemplate="Showing {first} to {last} of {totalRecords} entries"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown" rowsPerPageOptions={[5,10,15]}>
                        <Column selectionMode="multiple" style={{width:'3em'}}/>
                        <Column field="plateNumber" header="Plate Number" sortable filter filterPlaceholder="Search by Plate Number" />
                        <Column field="brand" sortField="brand" filterField="brand" header="Brand" sortable filter filterMatchMode="contains" filterPlaceholder="Search by Brand"/>
                        <Column field="model" sortField="model" filterField="model" header="Model" sortable filter filterMatchMode="contains" filterPlaceholder="Search by Model"/>
                        <Column field="year" header="Year" sortable  filter filterPlaceholder="Year" />
                        <Column field="color" header="Color"  sortable filter filterPlaceholder="Color" />
                    </DataTable>
                </div>
            </div>
        );
    }
}