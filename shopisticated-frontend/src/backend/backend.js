import axios from 'axios'

const BACKEND = axios.create({
  baseURL: 'http://localhost:8787'
})

export default BACKEND
