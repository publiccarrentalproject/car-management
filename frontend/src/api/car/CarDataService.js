import axios from 'axios'
import { API_URL } from '../../Constants'

class CarDataService {
    retrieveAllCars() {
        return axios.get(`${API_URL}/rest/cars?rentable=true`)
    }

    retrieveCar(plateNumber) {
        return axios.get(`${API_URL}/rest/car/${plateNumber}`)
    }

    createCar(car) {
        return axios.post(`${API_URL}/rest/car`, car)
    }

    deleteCar(plateNumber) {
        return axios.delete(`${API_URL}/rest/car/${plateNumber}`)
    }

    updateCar(plateNumber, car) {
        return axios.put(`${API_URL}/rest/car/${plateNumber}`, car)
    }
}

export default new CarDataService()