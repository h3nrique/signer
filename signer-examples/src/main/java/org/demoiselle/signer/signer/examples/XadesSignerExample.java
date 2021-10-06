package org.demoiselle.signer.signer.examples;

import org.demoiselle.signer.chain.icp.brasil.provider.impl.ICPBrasilUserHomeProviderCA;
import org.demoiselle.signer.core.keystore.loader.KeyStoreLoader;
import org.demoiselle.signer.core.keystore.loader.factory.KeyStoreLoaderFactory;
import org.demoiselle.signer.policy.engine.factory.PolicyFactory;
import org.demoiselle.signer.policy.impl.xades.XMLPoliciesOID;
import org.demoiselle.signer.policy.impl.xades.factory.XadesChecker;
import org.demoiselle.signer.policy.impl.xades.factory.XadesSigner;
import org.demoiselle.signer.policy.impl.xades.xml.impl.XMLChecker;
import org.demoiselle.signer.policy.impl.xades.xml.impl.XMLSigner;
import org.w3c.dom.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XadesSignerExample {

	private static final Logger LOGGER = Logger.getLogger(XadesSignerExample.class.getName());

	public static void main(String[] args) throws Throwable {

		String certAlias = "certificateAlias";
		String homeUser = ICPBrasilUserHomeProviderCA.PATH_HOME_USER;

		LOGGER.log(Level.INFO, "============= Iniciando aplicação =============");

		Path xmlFilePath = Paths.get(homeUser, "exemplo." + PolicyFactory.Policies.AD_RB_XADES_2_4 + ".xml");
		sign(xmlFilePath, certAlias, XMLPoliciesOID.AD_RB_XADES_2_4);

		LOGGER.log(Level.INFO, "============= Finalizando aplicação =============");

	}

	public static KeyStoreLoader keyStoreLoader;
	public static KeyStore keyStore;
	public static String alias;
	public static X509Certificate certificate;
	public static PrivateKey privateKey;
	// public static PinHandler pinHandler;
	public static String password;
	public static Certificate[] certificateChain;

	private static void sign(Path xmlFilePath, String certAlias, XMLPoliciesOID police)
		throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException {

		LOGGER.log(Level.INFO, "===== " + police.toString() + " =====");

		byte[] textXml = Files.readAllBytes(xmlFilePath);

		XMLSigner signer = XadesSigner.getInstance().factoryDefault();

		// if (pinHandler == null) {
		// pinHandler = new PinHandler("Ações");
		// }

		if (keyStoreLoader == null) {
			keyStoreLoader = KeyStoreLoaderFactory.factoryKeyStoreLoader();
//			 keyStoreLoader.setCallbackHandler(pinHandler);
			keyStore = keyStoreLoader.getKeyStore();
			if (keyStore.aliases().nextElement().equalsIgnoreCase(certAlias)) {
				certificate = (X509Certificate) keyStore.getCertificate(alias);
				privateKey = (PrivateKey) keyStore.getKey(alias, null);
				certificateChain = keyStore.getCertificateChain(alias);
			}
		}

		signer.setPrivateKey(privateKey);
		signer.setPolicyId(police.getOID());

		Document document = signer.signEnveloped(textXml);

		System.out.println(">>>>>>>>>> Validando a assinatura");

		// Valida
		XMLChecker checker = XadesChecker.getInstance().factoryDefault();
		checker.check(document);

	}

}
