import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api'

export async function auditUrl(url) {
  const response = await axios.post(`${API_BASE_URL}/audit`, { url })
  return response.data
}
