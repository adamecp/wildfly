/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.wildfly.clustering.marshalling.spi.util;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.function.Function;

import org.wildfly.clustering.marshalling.Externalizer;
import org.wildfly.clustering.marshalling.spi.IdentityFunction;
import org.wildfly.clustering.marshalling.spi.ObjectExternalizer;

/**
 * Externalizers for implementations of {@link SortedMap}.
 * Requires additional serialization of the comparator.
 * @author Paul Ferraro
 */
public class SortedMapExternalizer<T extends SortedMap<Object, Object>> extends ContextualMapExternalizer<T, Comparator<Object>> {
    @SuppressWarnings("unchecked")
    private static final Externalizer<Comparator<Object>> COMPARATOR_EXTERNALIZER = (Externalizer<Comparator<Object>>) (Externalizer<?>) new ObjectExternalizer<>(Comparator.class, Comparator.class::cast, IdentityFunction.getInstance());

    public SortedMapExternalizer(Class<T> targetClass, Function<Comparator<Object>, T> factory) {
        super(targetClass, factory, SortedMap::comparator, COMPARATOR_EXTERNALIZER);
    }
}
