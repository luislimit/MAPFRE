package com.mdval.ui.utils;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;

import org.apache.commons.collections.CollectionUtils;

import com.mdval.ui.PanelLogotipo;
import com.mdval.utils.DateFormatter;
import com.mdval.utils.LiteralesSingleton; 
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author federico
 *
 */
@Slf4j
public abstract class DialogSupport extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -637526827846474731L;

	protected LiteralesSingleton literales;

	@Getter
	protected PanelLogotipo panelLogo;

	@Getter
	protected Map<String, Object> params;

	@Getter
	protected Map<String, Object> returnParams;

	protected DateFormatter dateFormatter;

	private List<OnLoadListener> onLoadListeners;

	@Getter
	private FrameSupport frameParent;
	
	@Getter
	@Setter
	private Boolean errorOnload;

	/**
	 * 
	 */
	public DialogSupport() {
		super();

		initialize();
	}

	/**
	 * 
     * @param parent
     * @param modal
	 */
	public DialogSupport(FrameSupport parent, boolean modal) {
		super(parent, modal);
		this.frameParent = parent;
		initialize();
	}

	/**
	 * 
     * @param parent
     * @param modal
     * @param params
	 */
	public DialogSupport(FrameSupport parent, boolean modal, Map<String, Object> params) {
		super(parent, modal);
		this.params = params;
		this.frameParent = parent;
		initialize();
	}

	/**
	 * Proceso de inicialización
	 */
	private void initialize() {
		try {
			errorOnload = Boolean.FALSE;
			/*
			panelLogo = new PanelLogotipo("logotipo.png");
			panelLogo.setPreferredSize(new Dimension(286, 63));
*/
			dateFormatter = new DateFormatter();
			onLoadListeners = new ArrayList<>();
			returnParams = new HashMap<>();

                         //initComponents();
                        setupComponents();
                        
			initLiterals();
			initEvents();

			initModels();

			if (CollectionUtils.isNotEmpty(onLoadListeners)) {
				for (OnLoadListener l : onLoadListeners) {
					l.onLoad();
				}
			}

			initialState();
		} catch (IOException e) {
			log.warn("ERROR:", e);
		}
	}

/*	private void initComponents() {
		setupComponents();
		pack();
	}
*/
        /**
	 * Encapsula la creación de componentes
     * @param l
	 */
	protected void addOnLoadListener(OnLoadListener l) {
		this.onLoadListeners.add(l);
	}

	/**
	 * Instala los componentes internos de este dialog
	 */
	protected abstract void setupComponents();

	/**
	 * Instala los eventos a los componentes que los necesiten
	 */
	protected abstract void initEvents();

	/**
	 * Literales de los componentes
	 */
	protected abstract void setupLiterals();

	/**
	 * Inicia los modelos de combos, tablas, etc
	 */
	protected abstract void initModels();

	/**
	 * Estado inicial: los valores iniciales de los componentes que lo requieran
	 */
	protected abstract void initialState();

	/**
	 * Encapsula la creación de los literales
	 * 
	 * @throws IOException
	 */
	private void initLiterals() throws IOException {
		literales = LiteralesSingleton.getInstance();
		setupLiterals();
	}

        public void setTitulo(){
            String codigo = this.getClass().getSimpleName();
            String texto = literales.getLiteral(codigo + ".title");
            if (texto.isEmpty()){
               texto = codigo; 
            }
            setTitle(texto);
        }

        public void setTexto(Component component, String codigo){
            String texto = literales.getLiteral(this.getClass().getSimpleName() + "." + codigo);
            if (texto.isEmpty()){
               texto = literales.getLiteral(codigo);
               if (texto.isEmpty()){
                   texto = codigo;
               }
            }
            if (component instanceof AbstractButton){ //chk, btn
                ((AbstractButton)component).setText(texto);
            }else if (component instanceof JLabel){
                ((JLabel)component).setText(texto);
            }
        }

	/**
	 * @param width
	 * @param height
	 */
	public void setBounds(int width, int height) {
		Dimension dimension = new Dimension(width, height);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setResizable(Boolean.TRUE);
	}

	/**
	 * 
	 */
	public void forceRepaint() {
		// Corregir bug de no mostrado de resultados redimensionando la pantalla en 1
		// punto
		Integer width = getWidth();
		Integer height = getHeight();

		// sumamos 1 punto por dimension y redimensionamos
		Integer newWidth = width + 1;
		Integer newHeight = height + 1;
		setBounds(newWidth, newHeight);

		// Volver al tamaño original
		setBounds(width, height);
	}
}
