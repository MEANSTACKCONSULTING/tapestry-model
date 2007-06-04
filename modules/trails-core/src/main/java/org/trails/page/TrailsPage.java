/*
 * Created on Mar 15, 2005
 *
 * Copyright 2004 Chris Nelson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.trails.page;

import org.apache.tapestry.annotations.InjectObject;
import org.apache.tapestry.annotations.InjectState;
import org.apache.tapestry.event.PageBeginRenderListener;
import org.apache.tapestry.event.PageEvent;
import org.apache.tapestry.html.BasePage;
import org.trails.callback.CallbackStack;
import org.trails.callback.TrailsCallback;
import org.trails.descriptor.DescriptorService;
import org.trails.i18n.ResourceBundleMessageSource;
import org.trails.persistence.PersistenceService;

/**
 * @author fus8882
 *         <p/>
 *         TODO To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Style - Code Templates
 */
public abstract class TrailsPage extends BasePage implements PageBeginRenderListener
{

	public enum PageType
	{
		SEARCH, EDIT, LIST, VIEW
	}

	;

	public void pushCallback()
	{
		getCallbackStack().push(new TrailsCallback(getPageName()));
	}

	/**
	 * This property is injected with the callbackStack ASO
	 *
	 * @return
	 */
	@InjectState("callbackStack")
	public abstract CallbackStack getCallbackStack();

	/**
	 * This property is injected with the Spring persistenceService bean
	 *
	 * @return
	 */
	@InjectObject("spring:persistenceService")
	public abstract PersistenceService getPersistenceService();

	/**
	 * This property is injected with the Spring descriptorService bean
	 *
	 * @return
	 */
	@InjectObject("spring:descriptorService")
	public abstract DescriptorService getDescriptorService();

	/**
	 * Message source to i18n pages
	 *
	 * @return
	 */
	@InjectObject("spring:trailsMessageSource")
	public abstract ResourceBundleMessageSource getResourceBundleMessageSource();

	public void pageBeginRender(PageEvent event)
	{
		pushCallback();
	}

}