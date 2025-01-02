import React, { useState, useEffect } from "react";
import axios from "axios";
import './App.css'; // Asegúrate de tener los estilos en este archivo

function App() {
    const [provincias, setProvincias] = useState([]);
    const [cantones, setCantones] = useState([]);
    const [parroquias, setParroquias] = useState([]);
    const [selectedProvincia, setSelectedProvincia] = useState("");
    const [selectedCanton, setSelectedCanton] = useState("");
    const [selectedParroquia, setSelectedParroquia] = useState("");

    useEffect(() => {
        axios.get("http://localhost:8080/provincias")
            .then(response => {
                if (typeof response.data === "object") {
                    setProvincias(Object.entries(response.data));
                }
            })
            .catch(error => {
                console.error("Error fetching provincias", error);
            });
    }, []);

    useEffect(() => {
        if (selectedProvincia) {
            // Resetear canton y parroquia cuando se cambia la provincia
            setCantones([]);
            setParroquias([]);
            setSelectedCanton("");
            setSelectedParroquia("");

            axios.get(`http://localhost:8080/cantones/${selectedProvincia}`)
                .then(response => {
                    if (typeof response.data === "object") {
                        setCantones(Object.entries(response.data));
                    }
                })
                .catch(error => {
                    console.error("Error fetching cantones", error);
                });
        }
    }, [selectedProvincia]);

    useEffect(() => {
        if (selectedCanton && selectedProvincia) {
            axios.get(`http://localhost:8080/parroquias/${selectedProvincia}/${selectedCanton}`)
                .then(response => {
                    if (typeof response.data === "object") {
                        setParroquias(Object.entries(response.data));
                    }
                })
                .catch(error => {
                    console.error("Error fetching parroquias", error);
                });
        }
    }, [selectedCanton, selectedProvincia]);

    return (
        <div className="App">
            <div className="left-section">
                <h1>Seleccionar Provincia, Cantón y Parroquia</h1>

                <div className="select-container">
                    <label>Provincia:</label>
                    <select onChange={e => setSelectedProvincia(e.target.value)} value={selectedProvincia}>
                        <option value="">Seleccione Provincia</option>
                        {provincias.length > 0 && provincias.map(([id, nombre]) => (
                            <option key={id} value={id}>
                                {nombre}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="select-container">
                    <label>Cantón:</label>
                    <select onChange={e => setSelectedCanton(e.target.value)} value={selectedCanton} disabled={!selectedProvincia}>
                        <option value="">Seleccione Cantón</option>
                        {cantones.length > 0 && cantones.map(([id, nombre]) => (
                            <option key={id} value={id}>
                                {nombre}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="select-container">
                    <label>Parroquia:</label>
                    <select onChange={e => setSelectedParroquia(e.target.value)} value={selectedParroquia} disabled={!selectedCanton}>
                        <option value="">Seleccione Parroquia</option>
                        {parroquias.length > 0 && parroquias.map(([id, nombre]) => (
                            <option key={id} value={id}>
                                {nombre}
                            </option>
                        ))}
                    </select>
                </div>
            </div>

            <div className="right-section">
                <h2>Datos Seleccionados</h2>
                <div className="selected-info">
                    {selectedProvincia && (
                        <p><strong>Provincia:</strong> {selectedProvincia} - {provincias.find(([id]) => id === selectedProvincia)?.[1]}</p>
                    )}
                    {selectedCanton && (
                        <p><strong>Cantón:</strong> {selectedCanton} - {cantones.find(([id]) => id === selectedCanton)?.[1]}</p>
                    )}
                    {selectedParroquia && (
                        <p><strong>Parroquia:</strong> {selectedParroquia} - {parroquias.find(([id]) => id === selectedParroquia)?.[1]}</p>
                    )}
                </div>
            </div>
        </div>
    );
}

export default App;