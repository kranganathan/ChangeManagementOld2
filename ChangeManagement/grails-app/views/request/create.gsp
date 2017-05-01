<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'request.label', default: 'Request')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-request" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-request" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.request}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.request}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
             <g:form action="save">
                <label for="category">Category</label>
                <g:textField name="category" value="${request.category}"/>
                <br/>
                <label for="type">Type</label>
                <g:textField name="type" value="${request.type}"/>
                <br/>
                <label for="item">Item</label>
                <g:textField name="item" value="${request.item}"/>
                <br/>
                <label for="title">Title</label>
                <g:textField name="title" value="${request.title}"/>
                <br/>

                 <label for="description">Description</label>
                <g:textField name="description" value="${request.description}"/>
                <br/>
                <label for="urgency">Urgency</label>
                <g:select  name="urgency" from="${["Critical", "High", "Medium", "Low"]}"  />
                <br/>
                <label for="impact">Impact</label>
                <g:select  name="impact" from="${["Critical", "High", "Medium", "Low"]}"  />
                <br/>
                <label for="risk">Risk</label>
                <g:select  name="risk" from="${["High", "Medium", "Low"]}"  />
                <br/>
                 <label for="createdDate">Requested Date</label>
                 <g:field type="text" name="createdDate" placeholder="mm/dd/yyyy"/>
                 <br/>
                 <g:hiddenField name="state" value="Open" />
                 <g:hiddenField name="status" value="Submitted" />
                 <g:hiddenField name="phase" value="Submit" />

                <g:submitButton name="create" value="Save" />
            </g:form>
        </div>
    </body>
</html>
