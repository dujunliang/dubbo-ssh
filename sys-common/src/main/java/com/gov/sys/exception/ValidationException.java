package com.gov.sys.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Whenever a Query is issue to a method which is not supported, this exception
 * will be thrown
 * 
 * @author SPISTEV
 */
public class ValidationException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130628L;

	private static final String ERROR_CODE = "F-0023";

	private Collection<Violation> violations;
	
	public ValidationException(Violation violation) {
        super(ERROR_CODE);
	    violations = new ArrayList<Violation>();
	    violations.add(violation);
	    this.setAdditionalContext((Serializable) violation.toString());
	}

	public ValidationException(Collection<Violation> violations) {
		super(ERROR_CODE);
		this.violations = violations;
		String message = "";
		for(Violation violation: violations){
		    message = violation.toString();
		}
		this.setAdditionalContext(message);
	}
	
	public ValidationException(Serializable key) {
        super(ERROR_CODE, key);
    }
	
	public static final class Violation implements Serializable {
		
		/**
         * 
         */
        private static final long serialVersionUID = -7505779727807044025L;

        /**
		 * Must implements Serializable
		 */
		private Object object;

		private String description;
		
		/**
		 * Must implements Serializable
		 */
		private Object additionalContext;

		public Violation(String description) {
			this.description = description;
		}

		public Violation(Object object, String description,
				Object additionalContext) {
			super();
			this.object = object;
			this.description = description;
			this.additionalContext = additionalContext;
		}

		public Object getObject() {
			return object;
		}

		public void setObject(Object object) {
			this.object = object;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Object getAdditionalContext() {
			return additionalContext;
		}

		public void setAdditionalContext(Object additionalContext) {
			this.additionalContext = additionalContext;
		}

		@Override
		public String toString() {
			String obj = "";
			if (object != null) {
				obj = object.toString() + ": ";
			}

			String context = "";
			if (additionalContext != null) {
				context = "[" + additionalContext.toString() + "]";
			}

			return obj + description + context;
		}

	}

	public Collection<Violation> getViolations() {
		return violations;
	}

	public void setViolations(Collection<Violation> violations) {
		this.violations = violations;
	}

	 @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Iterator<Violation> itr = violations.iterator(); itr.hasNext();) {
			Violation violation = itr.next();
			sb.append(violation.toString());
			if(itr.hasNext()) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
		
}
