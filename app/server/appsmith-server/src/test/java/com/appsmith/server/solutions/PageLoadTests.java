package com.appsmith.server.solutions;

import com.appsmith.external.models.ActionConfiguration;
import com.appsmith.external.models.DatasourceConfiguration;
import com.appsmith.server.acl.AclPermission;
import com.appsmith.server.domains.Action;
import com.appsmith.server.domains.Application;
import com.appsmith.server.domains.ApplicationPage;
import com.appsmith.server.domains.Datasource;
import com.appsmith.server.domains.Layout;
import com.appsmith.server.domains.Organization;
import com.appsmith.server.domains.Page;
import com.appsmith.server.domains.Plugin;
import com.appsmith.server.services.ActionService;
import com.appsmith.server.services.ApplicationPageService;
import com.appsmith.server.services.OrganizationService;
import com.appsmith.server.services.PageService;
import com.appsmith.server.services.PluginService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@DirtiesContext
public class PageLoadTests {
    @Autowired
    OrganizationService organizationService;

    @Autowired
    ApplicationPageService applicationPageService;

    @Autowired
    PageService pageService;

    @Autowired
    PluginService pluginService;

    @Autowired
    ActionService actionService;

    Page page;

    @Before
    @WithUserDetails(value = "api_user")
    public void setup() {
        Organization organization = new Organization();
        organization.setName("PageLoad Test Organization");
        Organization savedOrganization = organizationService.create(organization).block();
        String organizationId = savedOrganization.getId();

        Application application = new Application();
        application.setName("PageLoad Test Application");
        application.setOrganizationId(organizationId);
        Application savedApplication = applicationPageService.createApplication(application, organizationId).block();

        List<ApplicationPage> pages = savedApplication.getPages();
        page = pageService.findById(pages.get(0).getId(), AclPermission.MANAGE_PAGES).block();

        Plugin installedPlugin = pluginService.findByName("Installed Plugin Name").block();

        Action httpAction = new Action();
        httpAction.setName("http action");
        Datasource datasource = new Datasource();
        datasource.setName("http datasource");
        datasource.setPluginId(installedPlugin.getId());
        datasource.setOrganizationId(organizationId);
        DatasourceConfiguration datasourceConfiguration = new DatasourceConfiguration();
        datasourceConfiguration.setUrl("http://some-url.com");
        datasource.setDatasourceConfiguration(datasourceConfiguration);
        httpAction.setDatasource(datasource);
        ActionConfiguration actionConfiguration = new ActionConfiguration();
        actionConfiguration.setHttpMethod(HttpMethod.GET);
        httpAction.setActionConfiguration(actionConfiguration);
        httpAction.setPageId(page.getId());

        actionService.create(httpAction).block();

    }

    @Test
    @WithUserDetails(value = "api_user")
    public void updateLayoutShouldAddActionToPageLoadActions() {
        Layout layout = new Layout();

    }
}
