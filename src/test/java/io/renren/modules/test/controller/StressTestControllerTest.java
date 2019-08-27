package io.renren.modules.test.controller;

import io.renren.RenrenApplication;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.test.entity.StressTestEntity;
import io.renren.modules.test.service.StressTestService;
import io.renren.modules.test.utils.StressTestUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest(classes = { RenrenApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class StressTestControllerTest extends AbstractTestNGSpringContextTests {

    private MockMvc mockMvc;
    @InjectMocks
    private StressTestController stressTestController;

    @Mock
    private StressTestService stressTestService;
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Before
    public void setUp() {
        StressTestController stressTestController = new StressTestController();
        mockMvc = MockMvcBuilders.standaloneSetup(stressTestController).build();
    }
    @Test
    public void testcase1(){
        Assert.assertTrue(true);
        System.out.println("testcase1");
    }

    @Test
    public void listTest(){
        Map<String,Object> params =new HashMap<String,Object>();
        params.put("caseName","性能测试");
        List<StressTestEntity> stressTestEntities = new ArrayList<StressTestEntity>();
        StressTestEntity stressTestEntity = new StressTestEntity();
        stressTestEntity.setCaseId(Long.parseLong("1"));
        stressTestEntity.setCaseName("性能测试");
        stressTestEntities.add(stressTestEntity);
        PowerMockito.when(stressTestService.queryList(new Query(StressTestUtils.filterParms(params)))).thenReturn(stressTestEntities);

        PowerMockito.when(stressTestService.queryTotal(new Query(StressTestUtils.filterParms(params)))).thenReturn(1);

        R returnPage = stressTestController.list(params);
        org.junit.Assert.assertNotNull(returnPage);

    }
}