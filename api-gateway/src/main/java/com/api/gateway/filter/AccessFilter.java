package com.api.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器类型，他决定过滤器在请求的哪个生命周期执行。这里定义为pre，代表会在请求被路由之前执行
     *
     * 在Zuul中默认定义了4中不同生命周期的过滤器类型
     *  1.pre: 在请求被路由之前调用
     *  2.routing：在路由请求时被调用
     *  3.post：在routing和error过滤器之后调用
     *  4.error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
     * 数值越小优先级越高
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否要执行。true表示该过滤器执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器执行的具体逻辑.
     * 这里我们通过ctx.setSendZuulResponse(false)令Zuul过滤该请求，不对其进行路由
     * 通过ctx.setResponseStatusCode(401)设置返回的错误码
     * 通过ctx.setResponseBody("access token is empty");对返回的内容进行编辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        String accessToken = request.getParameter("accessToken");
        if (accessToken == null){
            logger.error("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("access token is empty");
            return null;
        }
        logger.info("access token is ok");
        return null;
    }
}
