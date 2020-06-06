package org.apache.commons.lang3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DependencyListParser {

	private static final Pattern PATTERN = Pattern.compile("^\\s+(\\:?[^:]+)+$");

	private DependencyListParser() {
	}

	static class Dependency {

		private String groupId, artifactId, type, version, scope = null;

	}

	public static List<Dependency> parseDependencyList(final InputStream is) throws IOException {
		//
		List<Dependency> result = null;
		//
		try (final Reader r = is != null ? new InputStreamReader(is) : null;
				final BufferedReader br = r != null ? new BufferedReader(r) : null) {
			//
			String line = null;
			Matcher matcher = null;
			String[] ss = null;
			Dependency dependency = null;
			//
			while ((line = br != null ? br.readLine() : null) != null) {
				//
				if ((matcher = PATTERN.matcher(line)) == null || !matcher.matches()
						|| (ss = StringUtils.split(StringUtils.trim(matcher.group(0)), ':')) == null || ss.length < 5) {
					continue;
				}
				//
				if (result == null) {
					result = new ArrayList<>();
				}
				//
				(dependency = new Dependency()).groupId = ss[0];
				dependency.artifactId = ss[1];
				dependency.type = ss[2];
				dependency.version = ss[3];
				dependency.scope = ss[4];
				//
				result.add(dependency);
				//
			} // while
				//
		} //
			//
		return result;
		//
	}

}