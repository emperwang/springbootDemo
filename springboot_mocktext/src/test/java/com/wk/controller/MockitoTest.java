package com.wk.controller;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    // 验证行为
    @Test
    public void verifyBehaviour() {
        // mock createion
        List mockList = Mockito.mock(List.class);
        // using mock object
        mockList.add("one");
        mockList.clear();
        // 验证 add("one") 是否发生
        Mockito.verify(mockList).add("one");
        //Mockito.verify(mockList).add("1");
        Mockito.verify(mockList).clear();
    }

    // 测试桩
    @Test
    public void testStub() {
        // test 测试桩
        // mock具体的类型, 不仅仅是接口
        LinkedList mock = Mockito.mock(LinkedList.class);
        // 测试桩
        Mockito.when(mock.get(0)).thenReturn("first");
        // 输出 first, 因为已经为此设置了 stub
        System.out.println(mock.get(0));
        // 输出null, 此操作没有测试桩
        System.out.println(mock.get(999));
        // 测试 get(0) 调用的次数
        Mockito.verify(mock, Mockito.times(1)).get(0);
    }

    // 参数匹配器
    @Test
    public void parameterMatcher() {
        // mock一个对象
        LinkedList linkedList = Mockito.mock(LinkedList.class);
        // 使用内置的anyInt() 参数匹配器
        Mockito.when(linkedList.get(Mockito.anyInt())).thenReturn("element");
        // 使用自定义的参数匹配器( 在isValid() 函数中返回自己的匹配器实现)
        Mockito.when(linkedList.contains(Mockito.argThat(new IsValid())))
                .thenReturn(true);

        // 输出element
        System.out.println(linkedList.get(999));

        // 输出true
        System.out.println(linkedList.contains(1));
        // 也可以验证参数匹配器
        verify(linkedList).get(anyInt());
    }
    // 自定义的参数校验器
    class IsValid implements ArgumentMatcher<Object> {
        @Override
        public boolean matches(Object argument) {
            return (int)argument==1;
        }
    }

    // 验证函数的确切 最少  从未调用次数
    @Test
    public void verify_time(){
        LinkedList mockList = mock(LinkedList.class);
        mockList.add("one");
        mockList.add("two");
        mockList.add("two");
        mockList.add("three");
        mockList.add("three");
        mockList.add("three");
        mockList.add("four");
        mockList.add("five");
        mockList.add("six");
        // 验证 add("one") 调用一次
        verify(mockList).add("one");
        verify(mockList, times(1)).add("one");

        // 验证具体的执行次数
        verify(mockList, times(2)).add("two");
        verify(mockList, times(3)).add("three");

        // 使用never() 进行验证,相当于 times(0)
        verify(mockList, never()).add("never happened");

        // 使用atLeast / atMost() 验证
        // 至少调用一次
        verify(mockList, atLeastOnce()).add("three");
        // 至少调用2此
        verify(mockList, atLeast(2)).add("two");
        // 至多调用5次
        verify(mockList, atMost(5)).add("three");
    }

    // 返回值为void的函数 通过stub 抛出异常
    @Test
    public void verify_void(){
        LinkedList mockList = mock(LinkedList.class);
        doThrow(new RuntimeException("void")).when(mockList).clear();

        // 此会抛出异常
        mockList.clear();
    }

    // 验证执行顺序
    @Test
    public void verify_order(){
        // 验证 mock 一个对象的函数执行顺序
        List singleMock = mock(List.class);
        singleMock.add("was added first");
        singleMock.add("was added second");
        // 为该mock对象创建一个inOrder对象
        InOrder inOrder = inOrder(singleMock);

        // 确保add函数首先执行的是add("was added first")
        // 然后才是add("was added second")
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // 验证对个mock对象的函数执行顺序
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        firstMock.add("was called first");
        secondMock.add("was called second");
        // 为两个mock对象创建inOrder对象
        InOrder order = inOrder(firstMock, secondMock);
        // 验证他们的执行顺序
        order.verify(firstMock).add("was called first");
        order.verify(secondMock).add("was called second");
    }

    // 确保交互操作不会执行在 mock对象上
    @Test
    public void verify_interaction(){
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);
        mockOne.add("one");
        // 验证调用一次
        verify(mockOne).add("one");
        // 验证其没有调用过
        verify(mockOne, never()).add("two");

        // 验证零互动行为
        verifyZeroInteractions(mockThree, mockTwo);
    }

    // 找出冗余的互动(即未被验证到的)
    @Test
    public void verify_redunant(){
        LinkedList mockList = mock(LinkedList.class);
        mockList.add("one");
        mockList.add("two");

        verify(mockList).add("one");
        //verify(mockList).add("two");
        // 上面verify验证了add("one"), 没有验证到 add("two")
        // 所以会报错; 也就是说，存在未被验证到的 就会报错
        verifyNoMoreInteractions(mockList);
    }

    // 使用注解来快速的模拟对象
    @Mock
    private List mockList;
    @Test
    public void verify_shorthand(){
        // 必须初始化, 不然mockList的对象为null
        MockitoAnnotations.initMocks(this);
        this.mockList.add(1);
        verify(mockList).add(1);
    }

    // 为连续的调用做 stub
    //@Test(expected = RuntimeException.class)
    // todo ?? 为什么没有抛异常
    @Test
    public void test_continueinvoke(){
        MockitoAnnotations.initMocks(this);
        List mockList = mock(List.class);
        // 模拟连续调用时的返回期望值，如果分开，则只有最后一个生效
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);
        when(this.mockList.get(1)).thenReturn(0).thenReturn(1)
                .thenReturn(new RuntimeException());
        // 这里返回值都是2,为什么呢? 因为thenReturn分开写后,就是最后一个有效了,前面的都没有效果
        // 只有向 mockList.get(1)).thenReturn(0).thenReturn(1) 这样的写才是真正模拟连续调用的
        Assert.assertEquals(2, mockList.get(0));
        Assert.assertEquals(2, mockList.get(0));
        // 此时调换结果值就会返回错误，可见连续的thenReturn的返回值是有序的
        Assert.assertEquals(0, this.mockList.get(1));
        Assert.assertEquals(1, this.mockList.get(1));
        // 调用第三次 会抛出异常
        this.mockList.get(1);
        this.mockList.get(1);
    }

    // 为回调做 stub

    @Mock
    private List mockList1;

    @Test
    public void verify_callback(){
        // 使用Answer 来生成我们期望的返回
        when(mockList1.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return "hello world:"+args[0];
            }
        });

        Assert.assertEquals("hello world:0", mockList1.get(0));
        Assert.assertEquals("hello world:999", mockList1.get(999));
    }

    // 监控真实对象
    @Test
    public void verify_spy(){
        List<Object> list = new LinkedList<>();
        List<Object> spy = spy(list);
        // 为某函数 做stub
        when(spy.size()).thenReturn(100);
        // 通过spy对象调用真实对象的函数
        spy.add("one");
        spy.add("two");
        // 输出第一个元素
        System.out.println(spy.get(0));
        // 因为size() 函数被 stub了, 因此这里返回的就是100
        System.out.println(spy.size());
        // 交互验证
        verify(spy).add("one");
        verify(spy).add("two");
    }

    // 修改没有测试桩的调用 默认返回值
    @Test
    public void verify_unstubbed(){
        List mockUnstub = mock(List.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return 999;
            }
        });
        // 下面使用get(1) 没有预设stub,通常会犯规null, 但是使用了Answer改变了默认值
        Assert.assertEquals(999, mockUnstub.get(1));
        // size() 也是同样
        Assert.assertEquals(999, mockUnstub.size());
    }

    // 为下一个的断言捕获参数
    @Test
    public void verufy_capturing(){
        PersionDao persionDao = mock(PersionDao.class);
        PersonService personService = new PersonService(persionDao);
        ArgumentCaptor<Persion> argumentCaptor = ArgumentCaptor.forClass(Persion.class);
        personService.update(1, "jack");;

        verify(persionDao).update(argumentCaptor.capture());
        Assert.assertEquals(1, argumentCaptor.getValue().getId());
        Assert.assertEquals("jack", argumentCaptor.getValue().getName());
    }
    class Persion{
        private int id;
        private String name;

        public Persion(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    interface PersionDao{
        public void update(Persion persion);
    }

    class PersonService{
        private PersionDao persionDao;

        public PersonService(PersionDao persionDao) {
            this.persionDao = persionDao;
        }
        public void update(int id, String name){
            persionDao.update(new Persion(id, name));
        }
    }

    // 重置 mock 对象
    @Test
    public void verify_reset(){
        List list = mock(List.class);
        when(list.size()).thenReturn(10);

        list.add(1);
        Assert.assertEquals(10, list.size());
        // 重置mock, 重置所有的互动和预设
        reset(list);
        Assert.assertEquals(0, list.size());
    }
}

