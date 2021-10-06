/*
 * Demoiselle Framework
 * Copyright (C) 2016 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */

package org.demoiselle.signer.policy.impl.xades.factory;

import org.demoiselle.signer.core.factory.GenericFactory;
import org.demoiselle.signer.policy.impl.xades.xml.Signer;
import org.demoiselle.signer.policy.impl.xades.xml.impl.XMLSigner;

/**
 * Factory specialized in manufacturing objects for interface {@link Signer}
 *
 * @author Paulo Henrique Alves <paulohenriqueas13@gmail.com>
 */
public class XadesSigner extends GenericFactory<Signer> {

	public static final XadesSigner instance = new XadesSigner();

	public static final XadesSigner getInstance() {
		return XadesSigner.instance;
	}

	/**
	 * Defines a default object for the factory.
	 * The Component has a default implementation
	 *
	 * @return xades signer
	 * @see XMLSigner
	 */
	@Override
	public XMLSigner factoryDefault() {
		return new XMLSigner();
	}

	/**
	 * Defines the environment variable used by the abstract factory
	 * to fetch the name of the class to be fabricated.
	 * @return variable name
	 */
	@Override
	protected String getVariableName() {
		return "xades.implementation";
	}

}
