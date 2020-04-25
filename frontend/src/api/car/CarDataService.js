import axios from 'axios'
import { API_URL } from '../../Constants'

class CarDataService {
    retrieveAllCars(username) {
        return axios.get(`${API_URL}/cars`)
    }

    createCar(car) {
        return axios.post(`${API_URL}/car`, car)
    }

    deleteCar(plateNumber) {
        return axios.delete(`${API_URL}/car/${plateNumber}`)
    }

    updateCar(plateNumber, car) {
        return axios.put(`${API_URL}/car/${plateNumber}`, car)
    }
}

export default new CarDataService()