/**
 * 
 */

  function limpiarCamposApp(){
  	$('#nombreA').val('');
    $('#descripcionA').val('');
  };

  function limpiarCamposUsu(){
	  	$('#nickU').val('');
	    $('#nombreU').val('');
	    $('#apellidoU').val('');
	  };


  // Load the Visualization API and the piechart package.
  google.load('visualization', '1.0', {'packages':['corechart']});

  // Set a callback to run when the Google Visualization API is loaded.
  google.setOnLoadCallback(drawChart);
  
  // Callback that creates and populates a data table,
  // instantiates the pie chart, passes in the data and
  // draws it.
  function drawChart() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Servicio');
    data.addColumn('number', 'Cantidad de pedidos');
//    data.addRows(datosMagicos);
    data.addRows([
      ['Json', 3],
      ['Push', 1],
      ['User', 1],
    ]);

    // Set chart options
    var options = {'title':'Cantidad de pedidos HTTP realizados por Servicio',
                   'width':400,
                   'height':300};

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, options);
  }