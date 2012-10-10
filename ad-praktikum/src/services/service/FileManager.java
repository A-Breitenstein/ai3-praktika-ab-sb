package services.service;

import praktiukum1.MonitorRecord;

import java.util.Map;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 03.10.12
 * Time: 20:03
 */
public interface FileManager {

    /**
     * Returns an array containing numbers of the file at path.
     * The Numbers in that file have to be concatenated with white spaces, like " ".
     *
     * @param path URI as String to source of file
     * @return array with numbers of a file
     */
    int[] fetchFolgeFromFile(String path);

    /**
     * Exports algorithm statistics into a cvs file at the project home directory!
     *
     * @param recordMap map with algorithm statistics
     * @param iterations iterations of the algorithm loop
     */
    void exportCSV(Map<String, MonitorRecord> recordMap, int iterations);

    /**
     * Exports algorithm time-line statistics into a cvs file at the project home directory!
     *
     * @param recordMap map with algorithm statistics
     * @param iterations iterations of the algorithm loop
     */
    void exportTimelineCSV(Map<String, MonitorRecord> recordMap, int iterations);

}
