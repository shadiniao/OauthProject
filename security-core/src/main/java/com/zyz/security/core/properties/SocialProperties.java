package com.zyz.security.core.properties;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class SocialProperties {
	private QQProperties qq = new QQProperties();

	private GiteeProperties gitee = new GiteeProperties();
	private String filterProcessesUrl = "/auth";

	public GiteeProperties getGitee() {
		return gitee;
	}

	public void setGitee(GiteeProperties gitee) {
		this.gitee = gitee;
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}
}
