import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const demoCarList = [
    { plateNumber: 'AA-33-DD', brand: 'Toyota', model: 'Corolla', year: 2011, color: 'Gray' },
    { plateNumber: 'BB-44-CC', brand: 'Volkswagen', model: 'Polo', year: 2015, color: 'White' },
    { plateNumber: 'EE-33-UF', brand: 'Toyota', model: 'Yaris', year: 2018, color: 'Black' },
    { plateNumber: 'HH-13-1F', brand: 'Volkswagen', model: 'Jetta', year: 2017, color: 'Blue' },
    { plateNumber: 'XX-33-AA', brand: 'Volvo', model: 'S60', year: 2018, color: 'White' },
    { plateNumber: 'VV-45-GG', brand: 'Toyota', model: 'Yaris', year: 2010, color: 'White' },
    { plateNumber: 'UO-33-NN', brand: 'Toyota', model: 'Auris', year: 2018, color: 'Black' },
    { plateNumber: 'PO-56-MM', brand: 'Volkswagen', model: 'Jetta', year: 2012, color: 'Blue' },
    { plateNumber: 'JJ-78-NN', brand: 'BMW', model: 'X6', year: 2019, color: 'Blue' },
    { plateNumber: 'EE-23-EF', brand: 'Toyota', model: 'Corolla', year: 2014, color: 'Black' },
    { plateNumber: 'VT-89-CF', brand: 'Toyota', model: 'Yaris', year: 2014, color: 'Black' },
    { plateNumber: 'RG-75-HS', brand: 'Volkswagen', model: 'Polo', year: 2019, color: 'White' },
    { plateNumber: 'XX-33-AA', brand: 'Volvo', model: 'S40', year: 2013, color: 'White' }
];

const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
    },
}))(TableRow);

class CarListComponent extends Component {
    render() {

        return (
            <TableContainer component={Paper}>
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell align="right">Plate Number</StyledTableCell>
                            <StyledTableCell align="right">Brand</StyledTableCell>
                            <StyledTableCell align="right">Model</StyledTableCell>
                            <StyledTableCell align="right">Year</StyledTableCell>
                            <StyledTableCell align="right">Color</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {demoCarList.map((row) => (
                            <StyledTableRow key={row.name}>
                                <StyledTableCell align="right">{row.plateNumber}</StyledTableCell>
                                <StyledTableCell align="right">{row.brand}</StyledTableCell>
                                <StyledTableCell align="right">{row.model}</StyledTableCell>
                                <StyledTableCell align="right">{row.year}</StyledTableCell>
                                <StyledTableCell align="right">{row.color}</StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }
}

export default CarListComponent;