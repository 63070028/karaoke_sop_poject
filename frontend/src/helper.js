import axios from 'axios';


const Room = "http://localhost:3000/getRooms"
const Food = "http://localhost:3001/getAllFoodMenu"
const Order = "http://localhost:3002/getOrders"
const Payment = "http://localhost:3003/getPayments"


class Helpers {
    retrieveAllRoom() {
        return axios.get(Room)
    }

    retrieveAllOrder() {
        return axios.get(Order)
    }

    retrieveAllPayment() {
        return axios.get(Payment)
    }

    retrieveAllFood() {
        return axios.get(Food)
    }
}

export default new Helpers();