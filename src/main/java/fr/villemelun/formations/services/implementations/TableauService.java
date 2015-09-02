/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villemelun.formations.services.implementations;

import fr.villemelun.formations.dao.interfaces.IFormationsDAO;
import fr.villemelun.formations.dao.interfaces.IListevaleursDAO;
import fr.villemelun.formations.services.interfaces.ITableauService;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.WorkbookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nanard
 */
@Service
public class TableauService implements ITableauService {
    
     // FormationsDAO is injected...
    @Autowired
    IFormationsDAO formationsDAO;
    @Autowired
    IListevaleursDAO listeValeurDAO;
    
    @Transactional(readOnly = false)
    @Override
    public void createTableau() {
       
    }
    
    public IFormationsDAO getFormationsDAO() {
        return formationsDAO;
    }

    public void setFormationsDAO(IFormationsDAO formationsDAO) {
        this.formationsDAO = formationsDAO;
    }

    public IListevaleursDAO getListeValeurDAO() {
        return listeValeurDAO;
    }

    public void setListeValeurDAO(IListevaleursDAO listeValeurDAO) {
        this.listeValeurDAO = listeValeurDAO;
    }
}
