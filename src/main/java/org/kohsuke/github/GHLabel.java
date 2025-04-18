package org.kohsuke.github;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

// TODO: Auto-generated Javadoc
/**
 * The type GHLabel.
 *
 * @author Kohsuke Kawaguchi
 * @see <a href="https://developer.github.com/v3/issues/labels/">Labels</a>
 * @see GHIssue#getLabels() GHIssue#getLabels()
 * @see GHRepository#listLabels() GHRepository#listLabels()
 */
public class GHLabel extends GitHubInteractiveObject {

    /**
     * A {@link GHLabelBuilder} that creates a new {@link GHLabel}
     *
     * Consumer must call {@link Creator#done()} to create the new instance.
     */
    @BetaApi
    public static class Creator extends GHLabelBuilder<Creator> {
        private Creator(@Nonnull GHRepository repository) {
            super(Creator.class, repository.root(), null);
            requester.method("POST").withUrlPath(repository.getApiTailUrl("labels"));
        }
    }
    /**
     * A {@link GHLabelBuilder} that updates a single property per request
     *
     * {@link Setter#done()} is called automatically after the property is set.
     */
    @BetaApi
    public static class Setter extends GHLabelBuilder<GHLabel> {
        private Setter(@Nonnull GHLabel base) {
            super(GHLabel.class, base.getApiRoot(), base);
            requester.method("PATCH").setRawUrlPath(base.getUrl());
        }
    }
    /**
     * A {@link GHLabelBuilder} that allows multiple properties to be updated per request.
     *
     * Consumer must call {@link Updater#done()} to commit changes.
     */
    @BetaApi
    public static class Updater extends GHLabelBuilder<Updater> {
        private Updater(@Nonnull GHLabel base) {
            super(Updater.class, base.getApiRoot(), base);
            requester.method("PATCH").setRawUrlPath(base.getUrl());
        }
    }

    /**
     * Begins the creation of a new instance.
     *
     * Consumer must call {@link Creator#done()} to commit changes.
     *
     * @param repository
     *            the repository in which the label will be created.
     * @return a {@link Creator}
     */
    @BetaApi
    static Creator create(GHRepository repository) {
        return new Creator(repository);
    }

    /**
     * Reads a label from a repository.
     *
     * @param repository
     *            the repository to read from
     * @param name
     *            the name of the label
     * @return a label
     * @throws IOException
     *             the io exception
     */
    static GHLabel read(@Nonnull GHRepository repository, @Nonnull String name) throws IOException {
        return repository.root()
                .createRequest()
                .withUrlPath(repository.getApiTailUrl("labels"), name)
                .fetch(GHLabel.class);

    }

    /**
     * Reads all labels from a repository.
     *
     * @param repository
     *            the repository to read from
     * @return iterable of all labels
     */
    static PagedIterable<GHLabel> readAll(@Nonnull final GHRepository repository) {
        return repository.root()
                .createRequest()
                .withUrlPath(repository.getApiTailUrl("labels"))
                .toIterable(GHLabel[].class, null);

    }

    /**
     * To names.
     *
     * @param labels
     *            the labels
     * @return the collection
     */
    static Collection<String> toNames(Collection<GHLabel> labels) {
        List<String> r = new ArrayList<>();
        for (GHLabel l : labels) {
            r.add(l.getName());
        }
        return r;
    }

    @CheckForNull
    private String description;

    private long id;

    @JsonProperty("default")
    private boolean isDefault;

    private String nodeId;

    @Nonnull
    private String url, name, color;

    @JsonCreator
    private GHLabel(@JacksonInject @Nonnull GitHub root) {
        url = "";
        name = "";
        color = "";
        description = null;
    }

    /**
     * Delete this label from the repository.
     *
     * @throws IOException
     *             the io exception
     */
    public void delete() throws IOException {
        root().createRequest().method("DELETE").setRawUrlPath(getUrl()).send();
    }

    /**
     * Equals.
     *
     * @param o
     *            the o
     * @return true, if successful
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final GHLabel ghLabel = (GHLabel) o;
        return Objects.equals(url, ghLabel.url) && Objects.equals(name, ghLabel.name)
                && Objects.equals(color, ghLabel.color) && Objects.equals(description, ghLabel.description);
    }

    /**
     * Color code without leading '#', such as 'f29513'.
     *
     * @return the color
     */
    @Nonnull
    public String getColor() {
        return color;
    }

    /**
     * Purpose of Label.
     *
     * @return the description
     */
    @CheckForNull
    public String getDescription() {
        return description;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * Gets node id.
     *
     * @return the node id.
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    @Nonnull
    public String getUrl() {
        return url;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(url, name, color, description);
    }

    /**
     * If the label is one of the default labels created by GitHub automatically.
     *
     * @return true if the label is a default one
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Begins a single property update.
     *
     * @return a {@link Setter}
     */
    @BetaApi
    public Setter set() {
        return new Setter(this);
    }

    /**
     * Begins a batch update
     *
     * Consumer must call {@link Updater#done()} to commit changes.
     *
     * @return a {@link Updater}
     */
    @BetaApi
    public Updater update() {
        return new Updater(this);
    }

    /**
     * Gets the api root.
     *
     * @return the api root
     */
    @Nonnull
    GitHub getApiRoot() {
        return Objects.requireNonNull(root());
    }

}
