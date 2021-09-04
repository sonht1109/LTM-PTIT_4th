import React from 'react';
import logo from './logo.svg';
import './App.css';
import 'antd/dist/antd.css'; 
import { DatePicker } from 'antd';
import {FaAccusoft} from "react-icons/fa"; 
// 2.9k
function App() {
  return (
    <div className="App">
      <DatePicker/>
      <FaAccusoft/>
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
