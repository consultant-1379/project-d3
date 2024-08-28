var xmlhttp = new XMLHttpRequest();
//xmlhttp.open("GET", "js/myjson.txt", true);
//xmlhttp.send();
var url = "http://localhost:8080/jenkin" //localhost file
xmlhttp.open("GET",url,true);
xmlhttp.send();

var ctx = document.getElementById('canvas').getContext('2d');
var btm = document.getElementById('updatebtm');

btm.onclick =function doFunction(){
    window.selectedJob=document.getElementById('job-names').value;
    window.selectedTime=document.getElementById('time-range').value;
    var msg ="Job: " + selectedJob + " " + " Time: " +selectedTime;
    console.log(msg);
//    var testVar2=window.data.filter(d => d.blockedDurationMillis>100).filter(d =>d.months==selectedTime).map(function(elem){
//            var testd=elem.buildingDurationMillis/60000
//            return testd;
//    });
//        updateChart(myChart,selectedTime,testVar2)
}


xmlhttp.onreadystatechange =function (){

    if(this.readyState ==4 && this.status == 200){
            window.data = JSON.parse(this.responseText);
            //console.log(data)
            //This is working
//            var testVar=data.filter(d =>d.months==4);
//            var testF=testVar.map(function(elem){
//                var test2=elem.month
//                return test2;
//            });
            //end
//                     var testDays =testVar.map(function(elem){
//                         var testdays1=elem.days
//                         return testdays1;
//                     })
//                     var testDua =testVar.map(function(elem){
//                         var testd=elem.buildingDurationMillis/60000
//                         return testd;
//                     });
            window.duration = data.map(function(elem){
                  var durationInMin = elem.buildDuration/60000
                  return durationInMin.toFixed(0);
            });

            var months = data.map(function(elem){
                  return elem.buildMonth;
            });

//            var waitTime = data.map(function(elem){
//                  var waitTimeInSec = elem.waitingTimeMillis/1000
//                  return waitTimeInSec.toFixed(1);
//            });

             console.log(duration)

                     // createChart(testF,testDua,waitTime, testDays);
            createChart(months,duration)

    }
}

function randomNumber(min, max) {
    return Math.random() * (max - min) + min;
}

function updateChart(mychart,new_data,dur){

    mychart.destroy();
    myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [2,3,4,5,1,2,3,4,5,6,8],
            datasets:
            [{
                label: 'Build duration (MIN)',
                data: dur,
                backgroundColor: 'transparent',
                borderColor: "#3e95cd",
                borderWidth: 4
            },
            { // ADD new line
                label: 'Build wait time (SEC)',
                data: [2,3,4,5,1,2,3,4,5,6,8],
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

function createChart(testF,duration){
    console.log(testF,duration);
    window.myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: [1,2,3,4,5,6,7,8,9,10,11,12],
                datasets:
                [{
                    label: 'Build duration (MIN)',
                    data: duration,
                    backgroundColor: 'transparent',
                    borderColor: "#3e95cd",
                    borderWidth: 4
                },
                { // ADD new line
                    label: 'Build wait time (SEC)',
                    data: testF,
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