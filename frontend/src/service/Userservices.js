import axios from "axios";

export async function createOrder(data) {
  const response = await fetch(`http://localhost:3002/addOrder`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data),
    })
}

export async function createPayment(data) {
  const response = await fetch(`http://localhost:3003/addPayment`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    })
}

export async function updateOrder(data) {
  const response = await fetch(`http://localhost:3002/updateStatusOrder?orderId=${data}&status=payment`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
    })
}

export async function updatePayment(data) {
  const response = await fetch(`http://localhost:3003/updateStatusPayment?paymentId=${data}&status=complete`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
    })
}

export async function createFood(data) {
  const response = await fetch(`http://localhost:3001/addFoodMenu`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    })
}

export async function updateFood(data) {
  const response = await fetch(`http://localhost:3001/updateFoodMenu`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    })
}