package com.trifork.riaksearchpoc;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {
	
	private XYSeries series;
	private JFreeChart chart;

	Graph() {
		series = new XYSeries("Throughput");
		XYDataset xyDataset = new XYSeriesCollection(series);
		chart = ChartFactory.createXYLineChart
		                     ("Requests per second / Data size",
		                      "Stored records",
		                      "Requests per second",
		                      xyDataset,
		                      PlotOrientation.VERTICAL,
		                      true,
		                      false,
		                      false
		                     );
	}
	
	void addPoint(int totalRecords, int recordsPerSec) {
		series.add(totalRecords, recordsPerSec);
	}
	
	void writeToFile() {
		try {
			ChartUtilities.saveChartAsJPEG(new File("graph.jpg"), chart, 800, 600);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
