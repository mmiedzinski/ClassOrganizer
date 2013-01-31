package eu.anmore.classorganizer;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/**
	 * Plugin id.
	 */
	public static final String PLUGIN_ID = "eu.anmore.classorganizer";

	@Override
	public void start(BundleContext context) throws BundleException {
		try {
			super.start(context);
		} catch (Exception e) {
			LOGGER.error("Error while starting plugin.", e);
			throw new BundleException("Error while starting plugin.", e);
		}
	}

	@Override
	public void stop(BundleContext context) throws BundleException {
		try {
			super.stop(context);
		} catch (Exception e) {
			LOGGER.error("Error while stopping plugin.", e);
			throw new BundleException("Error while stopping plugin.", e);
		}
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(Activator.class);
}
