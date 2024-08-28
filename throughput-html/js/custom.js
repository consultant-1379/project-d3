var xmlhttp = new XMLHttpRequest();

xmlhttp.open("GET", "js/myjson.txt", true);
xmlhttp.send();

var btm = document.getElementById('updatebtm');
xmlhttp.onreadystatechange =function(){
    if(this.readyState ==4 && this.status == 200){
        var data = JSON.parse(this.responseText);
        //console.log(data)

        var duration = data.build_details.map(function(elem){
            var durationInMin = elem.buildingDurationMillis/60000
            return durationInMin.toFixed(0);
        });

        var months = data.build_details.map(function(elem){
            return elem.months;
        });

        var waitTime = data.build_details.map(function(elem){
            var waitTimeInSec = elem.waitingTimeMillis/1000
            return waitTimeInSec.toFixed(1);
        });
        console.log(duration)
        var ctx = document.getElementById('canvas').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: [1,2,3,4,5,6,7,8,9,10,11,12],
                datasets: 
                [{
                    label: 'Build duration (MIN)',
                    data: duration,
                    backgroundColor: 'transparent',
                    // [
                    //     'rgba(255,26,104,0.2)',
                    //     'rgba(54,162,235,0.2)',
                    //     'rgba(255,206,86,0.2)',
                    //     'rgba(75,192,192,0.2)',
                    //     'rgba(153,102,255,0.2)',
                    //     'rgba(255,159,64,0.2)',
                    //     'rgba(0,0,0,0.2)'
                    // ],
                    // borderColor: 'red',
                    borderColor: "#3e95cd",
                    borderWidth: 4
                },
                { // ADD new line
                    label: 'Build wait time (SEC)',
                    data: waitTime,
                    backgroundColor: 'transparent',
                    borderColor: 'green',
                    borderWidth: 4
                }]
            },
            options: {
                responsive: true,
                elements:{
                    line:{
                        tension:0
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                    }
                    
                }
            }
        });
    }
}

btm.onclick =function doFunction(){
    var selectedJob=document.getElementById('job-names').value;
    var selectedTime=document.getElementById('time-range').value;
    var msg ="Job: " + selectedJob + " " + " Time: " +selectedTime
    alert(msg);
}
