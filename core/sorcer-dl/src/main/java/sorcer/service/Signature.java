/*
 * Copyright 2009 the original author or authors.
 * Copyright 2009 SorcerSoft.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sorcer.service;

import sorcer.service.modeling.Variability;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A service <code>Signature</code> is an indirect behavioral feature of
 * {@link Exertion}s that declares a service that can be performed by instances
 * of {@link Service}s. It contains a service type and a selector of operation
 * of that service type (interface). Its implicit parameter and return value is
 * a service {@link Context}. Thus, the explicit signature of service-oriented
 * operations is defined by the same {@link Context} type for any exertion
 * parameter and return value . A signature may include a collection of optional
 * attributes describing a preferred {@link Service} with a given service type.
 * Also a signature can carry own implementation when its type is implemented
 * with the provided codebase.
 * <p>
 * In other words, a service signature is a specification of a service that can
 * be requested dynamically at the boundary of a service provider. Operations
 * include modifying a service {@link Context} or disclosing information about
 * the service context.
 *
 * @author Mike Sobolewski
 */
@SuppressWarnings("rawtypes")
public interface Signature extends Service, Comparable, Dependency, Identifiable, Arg, Serializable {

	/**
	 * Returns a name of this signature.
	 *
	 * @return name of signature
	 */
	public String getName();

	/**
	 * Returns an operation name of this signature.
	 *
	 * @return name of operation
	 */
	public String getSelector();

	/**
	 * Returns a fragment of operation of this signature. It's the part
	 * preceeding # in its selector.
	 *
	 * @return fragment of operation
	 */
	public String getPrefix();

	/**
	 * Returns a service provider name.
	 *
	 * @return name of service provider
	 */
	public String getProviderName();

	/**
	 * Returns a service provider.
	 *
	 * @return name of service provider
	 */
	public Object getProvider() throws SignatureException;

	/**
	 * Returns a provider of <code>Variability</code> type.
	 *
	 * @return Variability of this service provider
	 */
	public Variability<?> getVariability();

	public void setProviderName(String providerName);

	/**
	 * Returns a service type name of this signature.
	 *
	 * @return name of service interface
	 */
	public Class<?> getServiceType();

	/**
	 * Returns an array of service types of this signature
	 * to be matched by its service proxy.
	 *
	 * @return array of service types matched by service proxy
	 */
	public Class[] getMatchTypes();

	/**
	 * Assigns a path to the return value by this signature.
	 *
	 * @param path
	 *            to the return value
	 */
	public void setReturnPath(SignatureReturnPath path);

	public void setReturnPath(String path);

	/**
	 * Assigns a path to the return value with a path and directional attribute.
	 *
	 * @param path
	 *            to the return value
	 * @param direction
	 *            the path directional attribute
	 */
	public void setReturnPath(String path, Direction direction);

	/**
	 * Returns a path to the return value by this signature.
	 *
	 * @return path to the return value
	 */
	public SignatureReturnPath getReturnPath();

	/**
	 * Assigns a service type name of this signature.
	 *
	 * @return name of service interface
	 * @param serviceType
	 *            name of service interface
	 */
	public void setServiceType(Class<?> serviceType);

	/**
	 * Returns a signature type of this signature.
	 *
	 * @return a type of this signature
	 */
	public Type getType();

	/**
	 * Returns a inConnector specifying output paths for existing
	 * paths in returned context for this signature.
	 *
	 * @return a context mapping output paths to existing path
	 */
	public Context getInConnector();

	/**
	 * Assigns a signature <code>type</code> for this service signature.
	 *
	 * @param type
	 *            a signature type
	 */
	public Signature setType(Signature.Type type);

	/**
	 * Returns a codebase for the code implementing this signature. The codebase
	 * is a space separated string (list) of URls.
	 *
	 * @return a codebase for the code implementing this signature
	 */
	public String getCodebase();

	/**
	 * Assigns a codbase to <code>urls</code> for the code implementing this
	 * signature. The codebase is a space separated string (list) of URls.
	 *
	 * @param urls
	 *            a list of space separated URLS
	 */
	public void setCodebase(String urls);

	/**
	 *  Close and connectivity to the bound service provider.
	 * @throws RemoteException
	 * @throws IOException
	 */
	public void close() throws RemoteException, IOException;

	/**
	 * Returns a deployment for provisioning a referenced service provider;
	 *
	 */
	public Deployment getDeployment();


	/**
	 * There are four types of {@link Signature} operations that can be
	 * associated with signatures: <code>PRE</code> (preprocess),
	 * <code>PROC</code> (process/service) , <code>POST</code> (postprocess), and
	 * <code>APD_DATA</code> (append data) and code>APD_CONTROL</code> (append
	 * control strategy). Only one <code>PROC</code> signature can be associated
	 * with any exertion. The <code>PROC</code> signature defines an executing
	 * provider dynamically bounded at runtime. The <code>APD_DATA</code>
	 * signatures are invoked invoked first to get specified contexts from
	 * {@link sorcer.service.Contexter}s that are appended to the task's current
	 * context.
	 */
	public enum Type implements Arg {
		PROC, PRE, POST, SRV, APD_DATA, APD_CONTROL, BUILDER;

		/* (non-Javadoc)
		 * @see sorcer.service.Arg#getName()
		 */
		@Override
		public String getName() {
			return toString();
		}
	}

	/**
	 * Used to indicate if signature is active.
	 */
	public enum Operating implements Arg {
		YES, NO, TRUE, FALSE;

		/* (non-Javadoc)
		 * @see sorcer.service.Arg#getName()
		 */
		@Override
		public String getName() {
			return toString();
		}
	}

	public enum Kind implements Arg {
		TASKER, JOBBER, SPACER, DISPATCHER, OPTIMIZER, EXPLORER, MODEL, MODEL_MANAGER;

		/* (non-Javadoc)
		 * @see sorcer.service.Arg#getName()
		 */
		@Override
		public String getName() {
			return toString();
		}
	}

	public enum Direction implements Arg {
		IN, OUT, INOUT;

		/* (non-Javadoc)
		 * @see sorcer.service.Arg#getName()
		 */
		@Override
		public String getName() {
			return toString();
		}

		static public Direction fromString(String direction) {
			if (direction == null) {
				return null;
			} else if (direction.equals(""+IN)) {
				return IN;
			} else if (direction.equals(""+OUT)) {
				return OUT;
			} else if (direction.equals(""+INOUT)) {
				return INOUT;
			} else {
				return null;
			}
		}
	};

	static final String SELF = "_self_";
	static final String SELF_VALUE = "_self_value_";
	static final Type SRV = Type.PROC;
	static final Type PRE = Type.PRE;
	static final Type POST = Type.POST;
	static final Type APD = Type.APD_DATA;


	public static class Out extends ArrayList<Path> implements Arg {
		private static final long serialVersionUID = 1L;

		public Out() {
			super();
		}

		public Out(Path[] paths) {
			for (Path path : paths) {
				add(path) ;
			}
		}

		public Out(String[] names) {
			for (String name : names) {
				add(new Path(name)) ;
			}
		}

		public Out(int initialCapacity) {
			super(initialCapacity);
		}

		public String[] getPaths() {
			String[] paths = new String[size()];
			for (int i = 0; i < size(); i++)
				paths[i] = get(i).path();

			return paths;
		}

		public Path[] getSigPaths() {
			Path[] paths = new Path[size()];
			return this.toArray(paths);
		}

		@Override
		public String getName() {
			return toString();
		}

	}

	public static class In extends ArrayList<Path> implements Arg {
		private static final long serialVersionUID = 1L;

		public In() {
			super();
		}

		public In(Path[] paths) {
			for (Path path : paths) {
				add(path) ;
			}
		}
		public In(String[] names) {
			for (String name : names) {
				add(new Path(name)) ;
			}
		}

		public In(int initialCapacity) {
			super(initialCapacity);
		}

		public String[] getPaths() {
			String[] paths = new String[size()];
			for (int i = 0; i < size(); i++)
				paths[i] = get(i).path();

			return paths;
		}

		public Path[] getSigPaths() {
			Path[] paths = new Path[size()];
			return this.toArray(paths);
		}

		@Override
		public String getName() {
			return toString();
		}
	}

	public static class ReturnPath<T> implements SignatureReturnPath, Serializable, Arg {
		static final long serialVersionUID = 6158097800741638834L;
		public String path;
		public Direction direction;
		public Path[] outPaths;
		public Path[] inPaths;
		public Class<T> type;

		public ReturnPath() {
			// return the context
			path = Signature.SELF;
		}

		public ReturnPath(String path, Out argPaths) {
			this.path = path;
			if (argPaths != null && argPaths.size() > 0) {
				Path[] ps = new Path[argPaths.size()];
				this.outPaths = argPaths.toArray(ps);
				direction = Direction.OUT;
			}
		}

		public ReturnPath(String path, In argPaths) {
			this.path = path;
			if (argPaths != null && argPaths.size() > 0) {
				Path[] ps = new Path[argPaths.size()];
				this.inPaths = argPaths.toArray(ps);
				direction = Direction.IN;
			}
		}

		public ReturnPath(Out outPaths) {
			this(null, null, outPaths);
		}

		public ReturnPath(In inPaths) {
			this(null, inPaths, null);
		}

		public ReturnPath(String path, In inPaths, Out outPaths) {
			this.path = path;
			if (outPaths != null && outPaths.size() > 0) {
				Path[] ps = new Path[outPaths.size()];
				this.outPaths = outPaths.toArray(ps);
			}
			if (inPaths != null && inPaths.size() > 0) {
				Path[] ps = new Path[inPaths.size()];
				this.inPaths = inPaths.toArray(ps);
			}
			direction = Direction.INOUT;
		}

		public ReturnPath(String path, Path... argPaths) {
			this.path = path;
			if (argPaths != null && argPaths.length > 0) {
				this.outPaths = argPaths;
				direction = Direction.OUT;
			}
		}

		public ReturnPath(String path, Direction direction, Path... argPaths) {
			this.path = path;
			this.outPaths = argPaths;
			this.direction = direction;
		}

		public ReturnPath(String path, Direction direction,
						  Class<T> returnType, Path... argPaths) {
			this.path = path;
			this.direction = direction;
			this.outPaths = argPaths;
			type = returnType;
		}

		public String getName() {
			return path;
		}

		public String toString() {
			StringBuffer sb = new StringBuffer(path != null ? path : "no path");
			if (outPaths != null)
				sb.append("\noutPaths: " + Arrays.toString(outPaths));
			if (inPaths != null)
				sb.append("\ninPaths: " + Arrays.toString(inPaths));
			return sb.toString();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			ReturnPath that = (ReturnPath) o;

			if (!Arrays.equals(outPaths, that.outPaths)) return false;
			if (direction != that.direction) return false;
			if (!path.equals(that.path)) return false;
			if (type != null ? !type.equals(that.type) : that.type != null) return false;

			return true;
		}


		@Override
		public int hashCode() {
			int result = path.hashCode();
			result = 31 * result + (direction != null ? direction.hashCode() : 0);
			result = 31 * result + (outPaths != null ? Arrays.hashCode(outPaths) : 0);
			result = 31 * result + (inPaths != null ? Arrays.hashCode(outPaths) : 0);
			result = 31 * result + (type != null ? type.hashCode() : 0);
			return result;
		}

		public static String[] getPaths(Path[] paths) {
			String[] ps = new String[paths.length];
			for (int i = 0; i < paths.length; i++) {
				ps[i] = paths[i].path();
			}
			return ps;
		}

		public Path[] getInSigPaths() {
			return inPaths;
		}


		public Path[] getOutSigPaths() {
			return outPaths;
		}

		@Override
		public String getPath() {
			return path;
		}

		@Override
		public void setPath(String path) {
			this.path = path;
		}

		@Override
		public String[] getInPaths() {
			if (inPaths != null)
				return getPaths(inPaths);
			else
				return null;
		}

		@Override
		public String[] getOutPaths() {
			if (outPaths != null)
				return getPaths(outPaths);
			else
				return null;
		}
	}
}
