<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${catalog_name}</title>
  </head>
  <body>
    <h1> Catalog << ${catalog_name} >> </h1>

	<#list catalog_items>
	<h2> Catalog items: </h2>
	 <ul>
	    <#items as catalog_item>
	    <p> Item ${catalog_item?index} </p>
	 	<ul>
	 		<#if catalog_item.id??> <li> Id: ${catalog_item.id} </li> </#if>
	 		<#if catalog_item.author??> <li> Author: ${catalog_item.author} </li> </#if>
	 		<#if catalog_item.location??> <li> Location: ${catalog_item.location} </li> </#if>
	 		<#if catalog_item.title??> <li> Title: ${catalog_item.title} </li> </#if>
	 		<#if catalog_item.year??> <li> Year: ${catalog_item.year} </li> </#if>
	 		<#if catalog_item.type??> <li> Type: ${catalog_item.type} </li> </#if>
	 		<#list catalog_item.keywords>
	 		<h3> Keywords: </h3>
	 		<ul>
	 		<#items as keyword>
	 			<#if keyword??> <li> ${keyword} </li></#if>
	 		</#items>
	 		</ul>
	 		</#list>
	 	</ul>
		</#items>
	 </ul>
	<#else>
	  <p> No items
	</#list>
    
  </body>
</html>