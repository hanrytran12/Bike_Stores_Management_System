/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Product;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Huy
 */
public class SortService {

    public void sortByModelYearDesc(List<Product> listProductSearchByName) {
        listProductSearchByName.sort((o1, o2) -> {
            return o1.getModelYear() - o2.getModelYear(); //To change body of generated lambdas, choose Tools | Templates.
        });
    }

    public List<Product> sortList(List<Product> listProduct) {
        Collections.sort(listProduct, (p1, p2) -> {
            int yearComparison = Integer.compare(p2.getListPrice(), p1.getListPrice());
            if (yearComparison == 0) {
                return p1.getName().compareTo(p2.getName());
            }
            return yearComparison;
        });
        return listProduct;
    }
}
