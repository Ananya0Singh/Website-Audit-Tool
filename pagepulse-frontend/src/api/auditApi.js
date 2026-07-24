import axios from 'axios'

const API_BASE_URL = 'https://website-audit-tool-1lql.onrender.com/api'

export async function auditUrl(url) {
  const response = await axios.post(`${API_BASE_URL}/audit`, { url })
  return response.data
}
