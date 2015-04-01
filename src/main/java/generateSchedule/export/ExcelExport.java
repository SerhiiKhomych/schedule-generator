package generateSchedule.export;

import entities.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by sekh0713 on 10.02.2015.
 */
public class ExcelExport implements ExportBehaviour {
    public final static String PATH_NAME = "/resources/";
    public final static String FILE_NAME = "schedule.xls";

    public final static int STUDY_YEAR_ROW = 3;
    public final static int GROUP_ROW = 4;
    public final static int SEATS_ROW = 5;
    public final static int DAYS_ROW = 6;

    public final static int DAY_OF_WEEK_COLUMN = 0;
    public final static int COUPLE_NUMBER_COLUMN = 1;

    public final static int COUPLE_ROW_HEIGHT = 3;
    public final static int LEFT_MARGIN = 2;
    public final static int COUNT_WEEKS = 2;

    @Override
    public void exportSchedule(List<Couple> couples, List<Faculty> faculties, StudyYear[] studyYears, DayOfWeek[] dayOfWeeks, CoupleNumber[] coupleNumbers, List<Group> groups) {
        try {
            String resourcePath = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext())
                    .getResource("/WEB-INF/web.xml")
                    .getPath().replaceFirst("/WEB-INF/web.xml", PATH_NAME);
            FileOutputStream outputStream = new FileOutputStream(resourcePath + FILE_NAME);
            HSSFWorkbook workbook = new HSSFWorkbook();
            //Set styles
            HSSFFont hSSFFont = setFont(workbook.createFont());
            CellStyle coupleStyle = setCoupleStyle(workbook.createCellStyle());
            CellStyle fontStyle = setFontStyle(workbook.createCellStyle(), hSSFFont);
            CellStyle nameStyle = setNameStyle(workbook.createCellStyle(), hSSFFont);

            for (Faculty faculty : faculties) {
                HSSFSheet sh = workbook.createSheet(faculty.getShortName());
                HSSFRow studyYearRow = sh.createRow(STUDY_YEAR_ROW);
                HSSFRow groupRow = sh.createRow(GROUP_ROW);
                HSSFRow seatsRow = sh.createRow(SEATS_ROW);
                createScheduleRows(sh, dayOfWeeks.length * coupleNumbers.length);
                int countGroupsByYear = 0;
                for (StudyYear studyYear : studyYears) {
                    List<Group> groupsByStudyYear = Group.getGroupsByStudyYearAndFaculty(groups, studyYear, faculty);
                    int countGroups = 0;
                    for (Group group: groupsByStudyYear) {
                        // creating data cells
                        setCellData(studyYearRow.createCell(countGroupsByYear + LEFT_MARGIN),
                                studyYear.getLabel(),
                                nameStyle);
                        setCellData(groupRow.createCell(countGroupsByYear+countGroups + LEFT_MARGIN),
                                group.getName(),
                                nameStyle);
                        setCellData(seatsRow.createCell(countGroupsByYear+countGroups + LEFT_MARGIN),
                                group.getQuantity().toString(),
                                nameStyle);

                        int couplesPerWeek = 0;
                        int countDayOfWeeks = 0;
                        for (DayOfWeek dayOfWeek: dayOfWeeks) {
                            for (CoupleNumber coupleNumber: coupleNumbers) {
                                // calculating dayTypeOffset
                                int dayTypeOffset = getDayTypeOffset(dayOfWeek, coupleNumber);

                                // coupleRow
                                HSSFRow coupleRow = sh.getRow(couplesPerWeek + DAYS_ROW + dayTypeOffset);
                                coupleRow.setHeightInPoints((COUPLE_ROW_HEIGHT * sh.getDefaultRowHeightInPoints()));

                                // creating data cells
                                setCellData(coupleRow.createCell(DAY_OF_WEEK_COLUMN),
                                        dayOfWeek.getLabel(),
                                        fontStyle);
                                setCellData(coupleRow.createCell(COUPLE_NUMBER_COLUMN),
                                        coupleNumber.getStartDate(),
                                        fontStyle);
                                setCellData(coupleRow.createCell(countGroupsByYear + countGroups + LEFT_MARGIN),
                                        Couple.getCoupleForGroupByTime(couples, coupleNumber, dayOfWeek, group),
                                        coupleStyle);

                                // merging coupleNumberRows region
                                if (dayOfWeek.isFirstWeekDay()) {
                                    sh.addMergedRegion(new CellRangeAddress(couplesPerWeek + DAYS_ROW + dayTypeOffset,
                                            couplesPerWeek + DAYS_ROW + dayTypeOffset + 1,
                                            COUPLE_NUMBER_COLUMN,
                                            COUPLE_NUMBER_COLUMN));
                                }
                                couplesPerWeek++;
                            }

                            // merging dayOfWeekRows region
                            sh.addMergedRegion(new CellRangeAddress(DAYS_ROW + COUNT_WEEKS * countDayOfWeeks * CoupleNumber.values().length,
                                    DAYS_ROW + COUNT_WEEKS * countDayOfWeeks * CoupleNumber.values().length + (COUNT_WEEKS * CoupleNumber.values().length - 1),
                                    DAY_OF_WEEK_COLUMN, DAY_OF_WEEK_COLUMN));
                            countDayOfWeeks++;
                        }
                        countGroups++;
                    }

                    // merging studyYearColumns region
                    if (countGroups > 0) {
                        sh.addMergedRegion(new CellRangeAddress(STUDY_YEAR_ROW, STUDY_YEAR_ROW,
                                countGroupsByYear + LEFT_MARGIN,
                                countGroupsByYear + countGroups + LEFT_MARGIN - 1));
                    }
                    countGroupsByYear = countGroupsByYear + groupsByStudyYear.size();
                }
                autoSizeColumns(sh, countGroupsByYear);
            }
            outputStream.flush();
            workbook.write(outputStream);
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFilePath() {
        return PATH_NAME + FILE_NAME;
    }

    private void autoSizeColumns(HSSFSheet sheet, int countColumns) {
        for (int i=0; i < countColumns; i++) {
            sheet.autoSizeColumn((short) (LEFT_MARGIN + i));
        }
    }

    private int getDayTypeOffset(DayOfWeek dayOfWeek, CoupleNumber coupleNumber) {
        int dayTypeOffset;
        if (dayOfWeek.isFirstWeekDay()) {
            dayTypeOffset = coupleNumber.getCoupleNumber() - 1;
        } else {
            dayTypeOffset =  coupleNumber.getCoupleNumber() -  CoupleNumber.values().length;
        }
        return dayTypeOffset;
    }

    private CellStyle setCoupleStyle(CellStyle coupleStyle){
        coupleStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        coupleStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        coupleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        coupleStyle.setBottomBorderColor(HSSFCellStyle.BORDER_MEDIUM);
        coupleStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        coupleStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        coupleStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        coupleStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        coupleStyle.setWrapText(true);
        return coupleStyle;
    }

    private CellStyle setFontStyle(CellStyle fontStyle, HSSFFont hSSFFont){
        fontStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        fontStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
        fontStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        fontStyle.setBottomBorderColor(HSSFCellStyle.BORDER_MEDIUM);
        fontStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        fontStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        fontStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        fontStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        fontStyle.setRotation((short) 90);
        fontStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        fontStyle.setFont(hSSFFont);
        return fontStyle;
    }

    private HSSFFont setFont(HSSFFont hSSFFont) {
        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
        hSSFFont.setFontHeightInPoints((short) 20);
        hSSFFont.setColor(HSSFColor.BLACK.index);
        return hSSFFont;
    }

    private CellStyle setNameStyle(CellStyle nameStyle, HSSFFont hSSFFont){
        nameStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        nameStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        nameStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        nameStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        nameStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        nameStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        nameStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        nameStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        nameStyle.setAlignment(CellStyle.ALIGN_CENTER);
        nameStyle.setFont(hSSFFont);
        return nameStyle;
    }

    private void setCellData(HSSFCell cell, String value, CellStyle style) {
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void createScheduleRows(HSSFSheet sheet, int countRows) {
        for (int i = DAYS_ROW; i<= countRows + DAYS_ROW; i++) {
            sheet.createRow(i);
        }
    }
}