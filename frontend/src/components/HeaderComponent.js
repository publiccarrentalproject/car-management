import React, { Component } from 'react';
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'



class HeaderComponent extends Component {
    render() {
        return (<AppBar position="static">
            <Toolbar>
                <Typography variant="h6">
                    Car Service > Admin Page
                </Typography>
            </Toolbar>
        </AppBar>)
    }
}

export default HeaderComponent;