import axios, { AxiosRequestConfig } from 'axios';

// define common config for Axios
const instanceAxios = {
  baseURL: process.env.REACT_APP_SERVER,
};

const axiosConfig = axios.create(instanceAxios);

const request = ({ method, url, data, ...rest }: AxiosRequestConfig) =>
  axiosConfig({
    method: method,
    url: url,
    data: data,
    ...rest,
  });

const requestToken = ({ method, url, data, ...rest }: AxiosRequestConfig) => {
  let token = localStorage.getItem('token');

  return axiosConfig({
    method: method,
    url: url,
    data: data,
    headers: {
      Authorization: `CHAT_APP ${token}`,
    },
    ...rest,
  });
};

export { request, requestToken };
