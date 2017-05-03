/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Eduangelo Ferreira
 */
public interface MenuRepository extends DataRepository<Menu, Long> {

    public Menu findByPk(Long pk);
    public Iterable<Menu> publishedMenu();
}