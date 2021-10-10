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

package org.demoiselle.signer.policy.engine.asn1.etsi;

import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.demoiselle.signer.policy.engine.asn1.ASN1Object;
import org.demoiselle.signer.policy.engine.asn1.GeneralizedTime;

/**
 * The signingPeriod identifies the date and time before
 * which the signature policy should not be used for creating signatures,
 * and an optional date after which it should not be used for creating signatures.
 *
 * 		SigningPeriod ::= SEQUENCE {
 * 					notBefore {@link GeneralizedTime},
 * 					notAfter {@link GeneralizedTime} OPTIONAL }
 *
 */
public class SigningPeriod extends ASN1Object {

    private GeneralizedTime notBefore;
    private GeneralizedTime notAfter;

    public GeneralizedTime getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(GeneralizedTime notBefore) {
        this.notBefore = notBefore;
    }

    public GeneralizedTime getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(GeneralizedTime notAfter) {
        this.notAfter = notAfter;
    }

    @Override
    public void parse(ASN1Primitive derObject) {
        ASN1Sequence derSequence = ASN1Object.getDERSequence(derObject);

        this.notBefore = new GeneralizedTime();
        this.notBefore.parse(derSequence.getObjectAt(0).toASN1Primitive());

        if (derSequence.size() == 2) {
            this.notAfter = new GeneralizedTime();
            this.notAfter.parse(derSequence.getObjectAt(1).toASN1Primitive());
        }
    }

    @Override
    public String toString() {
        return this.notBefore.getDate() + " - " + (this.getNotAfter() != null ? this.getNotAfter().getDate() : "");
    }
}
