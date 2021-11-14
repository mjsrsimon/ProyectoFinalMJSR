package com.company.Vistas;

import com.company.Controladores.*;
import com.company.utils.HibernateUtil;

import org.hibernate.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaInicio {


    private JPanel JPGeneral;
    private JPanel JPVacio;
    private ControladorPieza cpieza;
    private ControladorProveedor cproveedor;


    public VentanaInicio(JFrame frame) {

        JMenuBar menuBar = new JMenuBar();

        /********************Menu Base de datos*****************/
        JMenu MenuBaseDatos = new JMenu("Base de Datos");
        menuBar.add(MenuBaseDatos);


        /********************Menu Pieza*****************/
        JMenu MenuPiezas = new JMenu("Pieza");

        JMenuItem itemAltaPieza = new JMenuItem("Alta");
        JMenuItem itemModificacionPieza = new JMenuItem("Modificacion");
        JMenuItem itemBajaPieza = new JMenuItem("Baja");
        JMenuItem itemListadoPieza = new JMenuItem("Listado");
        JMenu itemConsultaPiezas = new JMenu("Consulta de Pieza");

        JMenuItem itemCodigoPi = new JMenuItem("Por Codigo");
        JMenuItem itemNombrePi = new JMenuItem("Por Nombre");

        MenuPiezas.add(itemAltaPieza);
        MenuPiezas.add(itemModificacionPieza);
        MenuPiezas.add(itemBajaPieza);
        MenuPiezas.add(itemListadoPieza);

        itemConsultaPiezas.add(itemCodigoPi);
        itemConsultaPiezas.add(itemNombrePi);

        MenuPiezas.add(itemConsultaPiezas);
        menuBar.add(MenuPiezas);

        /***********************Menu Proveedor*********************/
        JMenu MenuProveedores = new JMenu("Proveedores");
        JMenuItem itemAltaProveedor = new JMenuItem("Alta");
        JMenuItem itemBajaProveedor = new JMenuItem("Baja");
        JMenuItem itemListadoProveedor = new JMenuItem("Listado");
        JMenuItem itemModificacionProveedor = new JMenuItem("Modificacion");
        JMenu itemConsultaProveedores = new JMenu("Consulta de Proveedores");

        JMenuItem itemCodigoProv = new JMenuItem("Por Codigo");
        JMenuItem itemNombreProv = new JMenuItem("Por Nombre");
        JMenuItem itemDireccionProv = new JMenuItem("Por Dirección");

        MenuProveedores.add(itemAltaProveedor);
        MenuProveedores.add(itemModificacionProveedor);
        MenuProveedores.add(itemBajaProveedor);
        MenuProveedores.add(itemListadoProveedor);

        itemConsultaProveedores.add(itemCodigoProv);
        itemConsultaProveedores.add(itemNombreProv);
        itemConsultaProveedores.add(itemDireccionProv);
        MenuProveedores.add(itemConsultaProveedores);
        menuBar.add(MenuProveedores);

        /***********************Menu Proyectos*********************/

        JMenu MenuProyectos = new JMenu("Proyectos");
        JMenuItem itemAltaProyecto = new JMenuItem("Alta");
        JMenuItem itemBajaProyecto = new JMenuItem("Baja");
        JMenuItem itemListadoProyecto = new JMenuItem("Listadp");
        JMenuItem itemModificacionProyecto = new JMenuItem("Modificacion");
        JMenu itemConsultaProyectos = new JMenu("Consulta de Proyectoes");

        JMenuItem itemCodigoProy = new JMenuItem("Por Codigo");
        JMenuItem itemNombreProy = new JMenuItem("Por Nombre");
        JMenuItem itemDireccionProy = new JMenuItem("Por Dirección");

        MenuProyectos.add(itemAltaProyecto);
        MenuProyectos.add(itemModificacionProyecto);
        MenuProyectos.add(itemBajaProyecto);
        MenuProyectos.add(itemListadoProyecto);

        itemConsultaProyectos.add(itemCodigoProy);
        itemConsultaProyectos.add(itemNombreProy);
        itemConsultaProyectos.add(itemDireccionProy);
        MenuProyectos.add(itemConsultaProyectos);
        menuBar.add(MenuProyectos);

        /*****************************************************************************/

        JMenu MenuGestionGlobal = new JMenu("Gestión Global");
        JMenu MenuAyuda = new JMenu("Ayuda");


        JMenuItem itemPPP = new JMenuItem("Pieza, Proveedores y Proyectos");
        JMenuItem itemSuministrosProveedor = new JMenuItem("Suministros por Proveedor");
        JMenuItem itemSuministrosPiezas = new JMenuItem("Suministros por Pieza");
        JMenuItem itemEstadisticas = new JMenuItem("Estadísticas");


        MenuGestionGlobal.add(itemPPP);
        MenuGestionGlobal.add(itemSuministrosProveedor);
        MenuGestionGlobal.add(itemSuministrosPiezas);
        MenuGestionGlobal.add(itemEstadisticas);

        menuBar.add(MenuGestionGlobal);
        menuBar.add(MenuAyuda);

        frame.add(menuBar); //Añadir el menu bar al frame. Se tiene que añadir al frame principal porque de este se arrastra a todos.
        frame.setJMenuBar(menuBar);

        /***************************** ACCIONES EN LOS MENUS ****************************/

        /**Cada vez que pulsemos en un item nos abrirá el panel inferior nuevo con los campos correspondientes a la tabla*/

        /**************************** PROVEEDORES ****************************************/


        itemAltaProveedor.addActionListener(e -> {
            VistaProveedores alta = new VistaProveedores();
            alta.getSpListado().setVisible(false);
            alta.getLbListaProveedores().setVisible(false);
            alta.getLbId().setVisible(false);
            alta.getLbIDProveedor().setVisible(false);
            cproveedor = new ControladorProveedor();
            mostrarPanel(alta.getJPProveedor());

        });
        itemModificacionProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaProveedores modificacion = new VistaProveedores();
                modificacion.renombrar("MODIFICAR");
                cproveedor = new ControladorProveedor();
                modificacion.mostrarProveedores(cproveedor.selectAll());
                mostrarPanel(modificacion.getJPProveedor());
            }
        });
        itemBajaProveedor.addActionListener(e -> {

        });
        itemListadoProveedor.addActionListener(e -> {
            ListarProveedores listado = new ListarProveedores();
            cproveedor = new ControladorProveedor();
            listado.setListaProveedores(cproveedor.selectAll());
            mostrarPanel(listado.getJPProveedorListado());
        });
        itemCodigoProv.addActionListener(e -> {
            Buscar buscarProveedorXCodigo = new Buscar();
            buscarProveedorXCodigo.consulta = "codproveedor";
            buscarProveedorXCodigo.tabla="Proveedor";
            mostrarPanel(buscarProveedorXCodigo.getJPBuscar());
        });
        itemNombreProv.addActionListener(e -> {
            Buscar buscarProveedorXNombre = new Buscar();
            buscarProveedorXNombre.consulta = "nombre";
            buscarProveedorXNombre.tabla="Proveedor";
            mostrarPanel(buscarProveedorXNombre.getJPBuscar());
        });
        itemDireccionProv.addActionListener(e -> {
            Buscar buscarProveedorXDireccion = new Buscar();
            buscarProveedorXDireccion.consulta = "direccion";
            buscarProveedorXDireccion.tabla="Proveedor";
            mostrarPanel(buscarProveedorXDireccion.getJPBuscar());
        });


        /**************************** PIEZAS ****************************************/

        itemAltaPieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaPiezas alta = new VistaPiezas();
                alta.getSpListado().setVisible(false);
                alta.getLbListaPiezas().setVisible(false);
                alta.getLbId().setVisible(false);
                alta.getLbIDPieza().setVisible(false);
                cpieza = new ControladorPieza();
                mostrarPanel(alta.getJPPieza());
            }
        });
        itemBajaPieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        itemModificacionPieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Necesito recuperar todas las piezas para mostrar en el listado
                 * */
                VistaPiezas modificacion = new VistaPiezas();
                modificacion.renombrar("MODIFICAR");
                modificacion.getSpListado().setVisible(true);
                modificacion.getLbListaPiezas().setVisible(true);
                cpieza = new ControladorPieza();
                modificacion.mostrarPiezas(cpieza.selectAll());
                mostrarPanel(modificacion.getJPPieza());
            }
        });
        itemListadoPieza.addActionListener(e -> {
            /*Necesito recuperar todas las piezas para mostrar en el listado
             * */
            ListarPiezas listado = new ListarPiezas();
            cpieza = new ControladorPieza();
            listado.setListaPiezas(cpieza.selectAll());
            mostrarPanel(listado.getJPPiezaListado());
        });
        itemCodigoPi.addActionListener(e -> {

            Buscar buscarPiezaXCodigo = new Buscar();
            buscarPiezaXCodigo.tabla="Pieza";
            buscarPiezaXCodigo.consulta = "codpieza";
            mostrarPanel(buscarPiezaXCodigo.getJPBuscar());

        });
        itemNombrePi.addActionListener(e -> {
            Buscar buscarPiezaXNombre = new Buscar();
            buscarPiezaXNombre.tabla="Pieza";
            buscarPiezaXNombre.consulta="nombre";
            mostrarPanel(buscarPiezaXNombre.getJPBuscar());
        });



        /** Menu Gestion Global  */
        itemPPP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        itemSuministrosProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        itemSuministrosPiezas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });

        itemEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    /**
     * Esta funcion nos permite reutilizar el frame y solo cambiar la parte inferior donde aparecen las pantallas de cliente
     * empleado, espectaculo....
     */
    public void mostrarPanel(JPanel panel) {

        JPVacio.removeAll();
        JPVacio.add(panel);
        JPVacio.repaint();
        JPVacio.revalidate();

    }


    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public JPanel getJPVacio() {
        return JPVacio;
    }

    public static void main(String[] args) {
        /**
         * Arrancamos la aplicacion desde este punto.
         * */
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();

        JFrame frame = new JFrame("El taller de Mª Jose");


        /*Añadimos un listener al frame principal para que cierre la conexion de
         * la base de datos que esté siendo usada.
         */

        //desde el frame estoy arrancado el menu para elegir la base de datos = parque.
        frame.setContentPane(new VentanaInicio(frame).JPGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}


