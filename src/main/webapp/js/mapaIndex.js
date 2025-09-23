document.addEventListener("DOMContentLoaded", function () {
    carregarMapaIndexDefault();
});

var map, ortofotoLayer;

atualizarMapaIndex = function () {
    let incluirMapaNaPesquisa = document.getElementById("incluirMapaNaPesquisaInput").value;
    let hasAreaIntervencao = document.getElementById("hasAreaIntervencao").value;
    let pesquisaPorNucleo = document.getElementById("pesquisaPorNucleo").value;
    console.log('incluir mapa na pesquisa: ' + incluirMapaNaPesquisa);
    console.log('tamanho da lista: ' + hasAreaIntervencao.length)
    console.log('pesquisa por nucleo' + pesquisaPorNucleo)
    if (incluirMapaNaPesquisa === 'true' && hasAreaIntervencao.length > 0 || pesquisaPorNucleo === 'true') {
        console.log(`Mapa Vetorizado: ${map}`);
        if (typeof map !== 'undefined' && map !== null) {
            // Remove o mapa do DOM e limpa os layers
            map.off();
            map.remove();
            console.log("Mapa reiniciado. 1");
        }

        carregarMapaIndex();
    } else {
        console.log(`Mapa Vetorizado: ${map}`);

        if (typeof map !== 'undefined' && map !== null) {
            // Remove o mapa do DOM e limpa os layers
            map.off();
            map.remove();
            console.log("Mapa reiniciado. 2");
        }
        carregarMapaIndexDefault();
    }

};

function carregarMapaIndexDefault() {
    // Inicializa o mapa centralizado em Teresina
    map = L.map('map', {
        center: [-5.082662, -42.749456], // Coordenadas de Teresina
        zoom: 12,
        trackResize: true // Ajusta o mapa automaticamente ao redimensionamento
    });
    // Adiciona a camada base do OpenStreetMap
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(map);
    // Camadas de dados
    var nucleoLayer = L.layerGroup().addTo(map);
    var quadraLayer = L.layerGroup().addTo(map);
    var loteLayer = L.layerGroup();
    // Adicionando camada de ortofoto
    var ortofotoLayer = L.tileLayer();
    // Controle de layers
    var overlayLayers = {
        "Núcleo": nucleoLayer,
        "Quadras": quadraLayer,
        "Lotes": loteLayer,
        "Ortofotos": ortofotoLayer
    };
    L.control.layers({}, overlayLayers).addTo(map);
    // Adiciona o botão de tela cheia
    L.control.fullscreen({
        position: 'topleft',
        pseudoFullscreen: true
    }).addTo(map);
    map.on('enterFullscreen', function () {
        console.log('Entrou em tela cheia');
    });
    map.on('exitFullscreen', function () {
        console.log('Saiu da tela cheia');
    });
    // Camadas para Núcleos, Quadras e Lotes
    var nucleoLayer = L.layerGroup().addTo(map);
    var quadraLayer = L.layerGroup().addTo(map);
    var loteLayer = L.layerGroup().addTo(map);
    // Função para capturar os bounds e carregar dados
    function loadMapDataByBounds() {
        var bounds = map.getBounds();
        var northEast = bounds.getNorthEast();
        var southWest = bounds.getSouthWest();
        console.log('Bounds:', bounds);
        var northEast = bounds.getNorthEast(); // Coordenada superior direita
        var southWest = bounds.getSouthWest(); // Coordenada inferior esquerda
        console.log(bounds);
        // Limpa as camadas antes de recarregar
        nucleoLayer.clearLayers();
        quadraLayer.clearLayers();
        loteLayer.clearLayers();
        // Aqui você pode adicionar uma chamada para carregar dados via AJAX
        // Exemplo: carregarDados(bounds);
    }

    // Eventos do mapa
    map.on('moveend', loadMapDataByBounds);
    map.on('zoomend', loadMapDataByBounds);
    // Carregar os dados iniciais ao carregar a página
    loadMapDataByBounds();
}

function carregarMapaIndex() {
    let nucleoLayer, quadraLayer, loteLayer;
    let coordenadaInput = document.getElementById("areIntervencaoCoordenadas").value.split(",");
    let listAreaIntervencao = JSON.parse(document.getElementById("areIntervencaoList").value);

    let latitude = (coordenadaInput[0] === undefined || coordenadaInput[0] === null || coordenadaInput[0] === '') ? latitudePadrao : coordenadaInput[0];
    let longitude = (coordenadaInput[1] === undefined || coordenadaInput[1] === null || coordenadaInput[1] === '') ? longitudePadrao : coordenadaInput[1];

    console.log(`Latitude: ${latitude}`);
    console.log(`Longitude: ${longitude}`);

    function initMap() {
        console.log("Inicializando mapa...");
        map = L.map('map', {
            center: [latitude, longitude],
            zoom: 16,
            maxZoom: 22,
            trackResize: true
        });

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
        }).addTo(map);

        nucleoLayer = L.layerGroup().addTo(map);
        quadraLayer = L.layerGroup().addTo(map);
        loteLayer = L.layerGroup();
        pontoLayer = L.layerGroup();

//        listAreaIntervencao.forEach(function (areaIntervencao) {
//            let pastaAreaIntervencao = (areaIntervencao.nome) + "/";
//            ortofotoLayer = L.tileLayer(linkServelet + pastaAreaIntervencao + '/{z}/{x}/{y}.jpg', {
//                attribution: 'Ortofotos',
//                maxZoom: 28,
//                tms: true
//            }).addTo(map);
//        });

        listAreaIntervencao.forEach(function (areaIntervencao) {
            areaIntervencao.nucleos.forEach(function (nucleo) {
                ortofotoLayer = L.tileLayer(linkServelet + nucleo.idNucleo + '/{z}/{x}/{y}.jpg', {
                    attribution: 'Ortofotos',
                    maxZoom: 28,
                    tms: true
                }).addTo(map);
            });
        });

        var overlayLayers = {
            "Núcleo": nucleoLayer,
            "Quadras": quadraLayer,
            "Lotes": loteLayer,
            "Ortofotos": ortofotoLayer
        };

        L.control.layers({}, overlayLayers).addTo(map);

        L.control.fullscreen({
            position: 'topleft',
            pseudoFullscreen: true
        }).addTo(map);

        map.on('enterFullscreen', function () {
            console.log('Entrou em tela cheia');
        });

        map.on('exitFullscreen', function () {
            console.log('Saiu da tela cheia');
        });

        addPolygonsToMap();

        // Adiciona evento para exibir/ocultar rótulos quando a camada for ativada/desativada
        map.on('overlayadd', function (event) {
            if (event.name === "Lotes") {
                toggleLoteLabels(true);
            }
        });

        map.on('overlayremove', function (event) {
            if (event.name === "Lotes") {
                toggleLoteLabels(false);
            }
        });

        map.on('moveend', capturarCoordenadasVisiveis);
        map.on('zoomend', toggleLabels);
        toggleLabels();
    }

    function capturarCoordenadasVisiveis() {
        var bounds = map.getBounds();
        var coordenadasVisiveis = {
            norteOeste: bounds.getNorthWest(),
            sulLeste: bounds.getSouthEast()
        };

        console.log("Coordenadas visíveis:");
        console.log("Noroeste:", coordenadasVisiveis.norteOeste);
        console.log("Sudeste:", coordenadasVisiveis.sulLeste);
    }

    function addPolygonsToMap() {
        console.log('veio para a construçãp dos poligonos');
        
        if (!listAreaIntervencao || listAreaIntervencao.length === 0) {
            console.warn("Nenhuma Área de intervencao disponível para adicionar ao mapa.");
            return;
        }

        listAreaIntervencao.forEach(function (areaIntervencao) {
            areaIntervencao.nucleos.forEach(function (nucleo) {
                var coordenadasNucleo = nucleo.coordenadas.map(function (coordenada) {
                    return [coordenada.latitude, coordenada.longitude];
                });

                L.polygon(coordenadasNucleo, {color: 'red', fillOpacity: 0.0}).addTo(nucleoLayer);

                nucleo.quadras.forEach(function (quadra) {
                    var coordenadasQuadra = quadra.coordenadas.map(function (coordenada) {
                        return [coordenada.latitude, coordenada.longitude];
                    });

                    var quadraPolygon = L.polygon(coordenadasQuadra, {color: 'green'}).addTo(quadraLayer);
                    var centroQuadra = quadraPolygon.getBounds().getCenter();

                    var quadraLabel = L.divIcon({
                        className: 'quadra-label',
                        html: '<div style="white-space: nowrap; text-align: center; font-size: 14px;">Q' + quadra.numero + '</div>',
                        iconSize: [0, 0]
                    });

                    var quadraMarker = L.marker(centroQuadra, {icon: quadraLabel}).addTo(quadraLayer);
                    quadraMarker.isQuadra = true;

                    quadra.lotes.forEach(function (lote) {
                        var coordenadasLote = lote.coordenadas.map(function (coordenada) {
                            return [coordenada.latitude, coordenada.longitude];
                        });

                        var numLote = lote.numero;
                        var numQuadra = lote.numeroQuadra;

                        var lotePolygon = L.polygon(coordenadasLote, {color: lote.corLote, fillOpacity: 0.4}).addTo(loteLayer);
                        var centroLote = lotePolygon.getBounds().getCenter();

                        var loteLabel = L.divIcon({
                            className: 'lote-label',
                            html: '<div style="white-space: nowrap; text-align: center; font-size: 12px;">L' + numLote + '</div>',
                            iconSize: [0, 0]
                        });

                        var loteMarker = L.marker(centroLote, {icon: loteLabel}).addTo(loteLayer);
                        loteMarker.isQuadra = false;
                        loteMarker.setOpacity(0);

                        lotePolygon.bindPopup(lote.textoPopup);

                        // Adiciona os pontos das coordenadas
                        lote.coordenadas.forEach(function (coordenada) {
                            var pontoIcon = L.divIcon({
                                className: 'custom-ponto-icon',
                                html: '<div class="ponto-container">' +
                                        '<div class="ponto-texto">' + (coordenada.nome ? coordenada.nome : "SN") + '</div>' +
                                        '</div>',
                                iconSize: [30, 30],
                                iconAnchor: [15, 15]
                            });

                            L.marker([coordenada.latitude, coordenada.longitude], {icon: pontoIcon}).addTo(pontoLayer);
                        });

                        lotePolygon.on('mouseover', function (e) {
                            var tooltip = L.tooltip({
                                permanent: false,
                                direction: 'top',
                                offset: [0, -10],
                                className: 'custom-tooltip'
                            })
                                    .setContent("Q:" + numQuadra + " L:" + numLote)
                                    .setLatLng(e.latlng)
                                    .addTo(map);

                            e.target.tooltip = tooltip;
                        });

                        lotePolygon.on('mouseout', function (e) {
                            if (e.target.tooltip) {
                                map.removeLayer(e.target.tooltip);
                                e.target.tooltip = null;
                            }
                        });
                    });
                });
            });
        });
    }


    function toggleLabels() {
        var currentZoom = map.getZoom();

        if (currentZoom > 18) {
            quadraLayer.eachLayer(function (layer) {
                if (layer instanceof L.Marker) {
                    if (layer.isQuadra) {
                        layer.setOpacity(0);
                    }
                }
            });

            if (map.hasLayer(quadraLayer)) {
                map.removeLayer(quadraLayer);
            }

            if (!map.hasLayer(loteLayer)) {
                map.addLayer(loteLayer);
            }

            // Mantém a lógica existente e adiciona o toggleLoteLabels
            toggleLoteLabels(true);
        }

        if (currentZoom <= 18) {
            if (!map.hasLayer(quadraLayer)) {
                map.addLayer(quadraLayer);
            }

            quadraLayer.eachLayer(function (layer) {
                if (layer instanceof L.Marker) {
                    if (layer.isQuadra) {
                        layer.setOpacity(1);
                    }
                }
            });

            loteLayer.eachLayer(function (layer) {
                if (layer instanceof L.Marker) {
                    if (!layer.isQuadra) {
                        layer.setOpacity(0);
                    }
                }
            });

            if (map.hasLayer(loteLayer)) {
                map.removeLayer(loteLayer);
            }

            // Adiciona o toggleLoteLabels para desabilitar os labels
            toggleLoteLabels(false);
        }

        if (currentZoom < 16) {
            quadraLayer.eachLayer(function (layer) {
                if (layer instanceof L.Marker) {
                    if (layer.isQuadra) {
                        layer.setOpacity(0);
                    }
                }
            });

            // Mantém a lógica existente e adiciona o toggleLoteLabels
            toggleLoteLabels(true);
        }

    }

    // Função ajustada conforme solicitado
    function toggleLoteLabels(show) {
        loteLayer.eachLayer(function (layer) {
            if (layer instanceof L.Marker) {
                if (!layer.isQuadra) {
                    layer.setOpacity(show ? 1 : 0);
                }
            }
        });
    }

    initMap();
}