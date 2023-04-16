var indonesia = ol.proj.fromLonLat([120.9842, -2.9639]);
var sriLanka = ol.proj.fromLonLat([80.7718, 7.8731]);
var china = ol.proj.fromLonLat([104.1954, 35.8617]);
var pakistan = ol.proj.fromLonLat([69.3451, 30.3753]);
var japan = ol.proj.fromLonLat([138.2529, 36.2048]);
var turkey = ol.proj.fromLonLat([38.9637, 35.2433]);

var mapData = [
    { geometry: new ol.geom.Point(indonesia), weight: 10 },
    { geometry: new ol.geom.Point(sriLanka), weight: 2 },
    { geometry: new ol.geom.Point(turkey), weight: 5 },
    { geometry: new ol.geom.Point(china), weight: 2 },
    { geometry: new ol.geom.Point(pakistan), weight: 3},
    { geometry: new ol.geom.Point(japan), weight: 1 }
];

var features = mapData.map(function(item) {
    var feature = new ol.Feature({
        geometry: item.geometry
    });
    feature.set('weight', item.weight);
    return feature;
});

var source = new ol.source.Vector({
    features: features
});

var heatmap = new ol.layer.Heatmap({
    source: source,
    blur: 20,
    radius: 15,
    gradient: ['#00f', '#0ff', '#0f0', '#ff0', '#f00']
});

var map = new ol.Map({
    target: 'map',
    layers: [
        new ol.layer.Tile({
            source: new ol.source.OSM()
        }),
        heatmap
    ],
    view: new ol.View({
        center: japan,
        zoom: 5
    })
});


var ctx = document.getElementById('warningChart').getContext('2d');
var warningsChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Terrorism', 'Transportation', 'Public Health', 'Civil Emergencies', 'Environmental Hazards', 'Natural Disasters'],
        datasets: [{
            label: 'Emergency Severity Status',
            data: [5, 5, 2, 2, 3, 1],
            borderColor: 'rgba(0, 188, 212, 1)',
            borderWidth: 1
        }]
    },
    options: {
        indexAxis: 'y',
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});


var ctx = document.getElementById('covidCasesChart').getContext('2d');
var covidCasesChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
        datasets: [{
            label: 'Number of Cases 2022',
            data: [19, 18, 7, 5, 3, 3],
            backgroundColor: 'rgba(0, 188, 212, 0.2)',
            borderColor: 'rgba(0, 188, 212, 1)',
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});

var ctx = document.getElementById('crimeChart').getContext('2d');
var crimeChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
        datasets: [{
            label: 'Cases of crime (100s)',
            data: [40, 40, 60, 30, 20, 20],
            backgroundColor: 'rgba(0, 188, 212, 0.2)',
            borderColor: 'rgba(0, 188, 212, 1)',
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});